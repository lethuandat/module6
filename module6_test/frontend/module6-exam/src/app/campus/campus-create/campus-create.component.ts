import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../employee";
import {CampusService} from "../campus.service";
import {EmployeeService} from "../employee.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {isDate} from "rxjs/internal-compatibility";

@Component({
  selector: 'app-campus-create',
  templateUrl: './campus-create.component.html',
  styleUrls: ['./campus-create.component.css']
})
export class CampusCreateComponent implements OnInit {
  campusForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required, this.dateNotExist]),
    address: new FormControl('', [Validators.required]),
    employee: new FormControl('', [Validators.required])
  });

  employee: Employee[] = [];
  isExitsName = false;
  check = false;

  constructor(private campusService: CampusService,
              private employeeService: EmployeeService,
              private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getEmployee();
  }

  getEmployee(): void {
    this.employeeService.getAll().subscribe(employees => {
      this.employee = employees;
    });
  }

  dateNotExist(abstractControl: AbstractControl) {
    const v = abstractControl.value;
    const start = new Date(v);
    if (!isDate(start)) {
      return {dateNotExist: true, message: '*Vui lòng nhập ngày hợp lệ'};
    }
  }

  submit() {
    this.check = true;
    const campus = this.campusForm.value;

    this.employeeService.findById(campus.employee).subscribe(employee => {
      campus.employee = {
          id: employee.id
        };
      campus.isDeleted = false;
        this.campusService.save(campus).subscribe(() => {
          this.toastrService.success('Thêm mới thành công!', 'Thông báo');
          this.router.navigate(['/campus/list']);
        }, e => console.log(e));
      }
    );
  }

  checkName($event: EventTarget) {
    this.campusService.checkName(String($event)).subscribe(value => {
        if (value) {
          this.isExitsName = true;
        } else {
          this.isExitsName = false;
        }
      }
    );
  }
}
