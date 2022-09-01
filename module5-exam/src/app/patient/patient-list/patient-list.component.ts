import {Component, OnInit} from '@angular/core';
import {Patient} from '../../model/patient';
import {Patienter} from '../../model/patienter';
import {PatientService} from '../patient.service';
import {PatienterService} from '../patienter.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patient: Patient[] = [];
  patientNoPagination: Patient[] = [];
  patienter: Patienter[] = [];
  deleteId: number;
  indexPagination = 1;
  totalPagination: number;
  doctor = '';
  name = '';
  reason: '';
  method: '';
  dayIn = '1990-01-01';
  dayOut = '2099-12-31';

  constructor(private patientService: PatientService,
              private patienterService: PatienterService,
              private modalService: NgbModal,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.getAll();
  }


  getAll(): void {
    this.patientService.getAll(0).subscribe(patient => {
      this.patient = patient;
    });

    this.patienterService.getAll().subscribe(patienter => {
      this.patienter = patienter;
    });

    this.patientService.getAllNoPagination().subscribe(patient => {

      this.patientNoPagination = patient;

      if ((this.patientNoPagination.length % 5) !== 0) {
        this.totalPagination = (Math.round(this.patientNoPagination.length / 5)) + 1;
      }
    });
  }

  openDelete(targetModal, patient: Patient): void {
    this.deleteId = patient.id;
    this.modalService.open(targetModal, {
      backdrop: 'static',
      size: 'md'
    });
  }

  onDelete(): void {
    this.patientService.delete(this.deleteId).subscribe(() => {
      this.ngOnInit();
      this.toast.error('Xóa thành công!', 'Thông báo');
      this.modalService.dismissAll();
    }, e => console.log(e));
  }

  search() {
    this.patientService.find((this.indexPagination * 5) - 5, this.doctor, this.name, this.reason, this.method, this.dayIn, this.dayOut).subscribe(patient => {
      this.patient = patient;
      this.doctor = '';
      this.name = '';
      this.reason = '';
      this.method = '';
      this.dayIn = '1990-01-01';
      this.dayOut = '2099-12-31';
    });
  }

  findPagination() {
    this.patientService.getAll((this.indexPagination * 5) - 5).subscribe(patient => {
      this.patient = patient;
    });
  }

  indexPaginationChange(value: number) {
    this.indexPagination = value;
  }

  firstPage() {
    this.indexPagination = 1;
    this.ngOnInit();
  }

  nextPage() {
    this.indexPagination = this.indexPagination + 1;
    if (this.indexPagination > this.totalPagination) {
      this.indexPagination = this.indexPagination - 1;
    }
    this.patientService.getAll((this.indexPagination * 5) - 5).subscribe(patient => {
      this.patient = patient;
    });
  }

  previousPage() {
    this.indexPagination = this.indexPagination - 1;
    if (this.indexPagination === 0) {
      this.indexPagination = 1;
      this.ngOnInit();
    } else {
      this.patientService.getAll((this.indexPagination * 5) - 5).subscribe(patient => {
        this.patient = patient;
      });
    }
  }

  lastPage() {
    this.indexPagination = this.patientNoPagination.length / 5;
    this.patientService.getAll((this.indexPagination * 5) - 5).subscribe(patient => {
      this.patient = patient;
    });
  }
}
