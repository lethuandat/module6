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
  patienter: Patienter[] = [];
  deleteId: number;
  page = 1;
  pageSize = 5;
  keyword = '';
  keyword2 = '';

  constructor(private patientService: PatientService,
              private patienterService: PatienterService,
              private modalService: NgbModal,
              private toast: ToastrService) {
  }

  ngOnInit(): void {
    this.getAll();
  }


  getAll(): void {
    this.patientService.getAll().subscribe(patient => {
      this.patient = patient;
    });
    this.patienterService.getAll().subscribe(patienter => {
      this.patienter = patienter;
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
    this.patientService.find(this.keyword, this.keyword2).subscribe(patient => {
      this.patient = patient;
      this.keyword = '';
      this.keyword2 = '';
    });
  }
}
