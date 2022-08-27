import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Patienter} from '../../model/patienter';
import {PatientService} from '../patient.service';
import {PatienterService} from '../patienter.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-patient-create',
  templateUrl: './patient-create.component.html',
  styleUrls: ['./patient-create.component.css']
})
export class PatientCreateComponent implements OnInit {
  patientForm: FormGroup = new FormGroup({
    patienter: new FormControl(''),
    dayIn: new FormControl('', [Validators.required, this.checkDayIn]),
    dayOut: new FormControl('', [Validators.required, this.checkDayOut]),
    reason: new FormControl('', [Validators.required]),
    method: new FormControl('', [Validators.required]),
    doctor: new FormControl('', [Validators.required]),
  }, this.checkDay);

  patienter: Patienter[] = [];

  constructor(private patientService: PatientService,
              private patienterService: PatienterService,
              private modalService: NgbModal,
              private toast: ToastrService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getPatienter();
  }

  getPatienter(): void {
    this.patienterService.getAll().subscribe(patienter => {
      this.patienter = patienter;
    });
  }

  submit() {
    const patient = this.patientForm.value;

    this.patienterService.findById(patient.patienter).subscribe(patienter => {
        patient.patienter = {
          id: patienter.id,
          name: patienter.name
        };
        this.patientService.save(patient).subscribe(() => {
          this.toast.success('Thêm mới thành công!', 'Thông báo');
          this.router.navigate(['/patient/list']);
        }, e => console.log(e));
      }
    );
  }

  checkDayIn(control: AbstractControl) {
    const now = new Date();

    const dateIn = new Date(control.value);

    if (dateDiff(dateIn, now) <= 0) {
      return {dayInError: true};
    }
    return null;
  }

  checkDayOut(control: AbstractControl) {
    const now = new Date();

    const dateOut = new Date(control.value);

    if (dateDiff(dateOut, now) <= 0) {
      return {dayOutError: true};
    }
    return null;
  }

  checkDay(control: AbstractControl) {
    const dateIn = new Date(control.value.dayIn);

    const dateOut = new Date(control.value.dayOut);


    if (dateDiff(dateIn, dateOut) < 0) {
      return {dayError: true};
    }

    return null;
  }
}

function dateDiff(first, second) {
  return Math.round((second - first) / (1000 * 60 * 60 * 24));
}
