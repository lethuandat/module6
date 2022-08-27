import {Component, OnInit} from '@angular/core';
import {PatientService} from '../patient.service';
import {PatienterService} from '../patienter.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Patienter} from '../../model/patienter';
import {Patient} from '../../model/patient';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.css']
})
export class PatientEditComponent implements OnInit {
  patientForm: FormGroup;
  patienter: Patienter[] = [];
  id: number;

  constructor(private patientService: PatientService,
              private patienterService: PatienterService,
              private modalService: NgbModal,
              private toast: ToastrService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.id = +paramMap.get('id');
      this.patientService.findById(this.id).subscribe(patient => {
        this.patientForm = new FormGroup({
          id: new FormControl(patient.id),
          patienter: new FormControl(patient.patienter.id),
          name: new FormControl(patient.patienter.name, [Validators.required, Validators.pattern('^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$')]),
          dayIn: new FormControl(patient.dayIn, [Validators.required, this.checkDayIn]),
          dayOut: new FormControl(patient.dayOut, [Validators.required, this.checkDayOut]),
          reason: new FormControl(patient.reason, [Validators.required]),
          method: new FormControl(patient.method, [Validators.required]),
          doctor: new FormControl(patient.doctor, [Validators.required]),
        }, this.checkDay);
      });
    });
  }

  ngOnInit(): void {
    this.getPatienter();
  }

  getPatienter(): void {
    this.patienterService.getAll().subscribe(patienter => {
      this.patienter = patienter;
    });
  }

  update(id: number): void {
    let patient: Patient;

    this.patienterService.findById(this.patientForm.value.patienter).subscribe(patienterF => {
        patienterF = {
          id: this.patientForm.value.patienter,
          name: this.patientForm.value.name
        };
        patient = {
          id: this.patientForm.value.id,
          patienter: {
            id: patienterF.id,
            name: patienterF.name
          },
          dayIn: this.patientForm.value.dayIn,
          dayOut: this.patientForm.value.dayOut,
          reason: this.patientForm.value.reason,
          method: this.patientForm.value.method,
          doctor: this.patientForm.value.doctor
        };
        this.patienterService.update(patienterF.id, patienterF).subscribe(() => {
        });
        this.patientService.update(id, patient).subscribe(() => {
          this.toast.info('Cập nhật thành công!', 'Thông báo');
          this.router.navigate(['/patient/list']);
        });
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
