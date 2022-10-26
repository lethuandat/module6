import {Component, OnInit} from '@angular/core';
import {Campus} from "../campus";
import {CampusService} from "../campus.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-campus-list',
  templateUrl: './campus-list.component.html',
  styleUrls: ['./campus-list.component.css']
})
export class CampusListComponent implements OnInit {
  campus: Campus[] = [];
  number: number;
  indexPagination = 0;
  totalPage: Array<number>;
  previousPageStyle = 'inline-block';
  nextPageStyle = 'inline-block';
  totalElements = 0;
  pageSize: number;
  displayPagination = 'inline-block';
  numberOfElement = 0;
  campusName = '';
  employeeName = '';
  nameDelete: string;
  idDelete: number;

  infoId: number;
  infoName: string;
  infoDate: string;
  infoGender: string;
  infoPlacement: string;

  constructor(private campusService: CampusService,
              private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.campusService.findAll(this.indexPagination, this.campusName, this.employeeName, this.pageSize).subscribe((result?: any) => {
      if (result === null) {
        this.totalPage = new Array(0);
        this.campus = [];
        this.displayPagination = 'none';
      } else {
        this.number = result?.number;
        this.pageSize = result?.size;
        this.numberOfElement = result?.numberOfElements;
        this.campus = result.content;
        this.totalElements = result?.totalElements;
        this.totalPage = new Array(result?.totalPages);
      }
      this.checkPreviousAndNext();
    });
  }

  previousPage(event: any) {
    event.preventDefault();
    this.indexPagination--;
    this.ngOnInit();
  }

  nextPage(event: any) {
    event.preventDefault();
    this.indexPagination++;
    this.ngOnInit();
  }

  checkPreviousAndNext() {
    if (this.indexPagination === 0) {
      this.previousPageStyle = 'none';
    } else if (this.indexPagination !== 0) {
      this.previousPageStyle = 'inline-block';
    }
    if (this.indexPagination < (this.totalPage.length - 1)) {
      this.nextPageStyle = 'inline-block';
    } else if (this.indexPagination === (this.totalPage.length - 1) || this.indexPagination > (this.totalPage.length - 1)) {
      this.nextPageStyle = 'none';
    }
  }

  totalElement($event: any) {
    switch ($event.target.value) {
      case '5':
        this.pageSize = 5;
        this.indexPagination = 0;
        this.ngOnInit();
        break;
      case '10':
        this.pageSize = 10;
        this.indexPagination = 0;
        this.ngOnInit();
        break;
      case '15':
        this.pageSize = 15;
        this.indexPagination = 0;
        this.ngOnInit();
        break;
      case 'Tất cả':
        this.pageSize = this.totalElements;
        this.indexPagination = 0;
        this.ngOnInit();
        break;
    }
  }

  checkRegex(content: string): boolean {
    const format = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    return format.test(content);
  }

  search() {
    if (this.checkRegex(this.campusName) || this.checkRegex(this.employeeName)) {
      this.indexPagination = 0;
      this.totalPage = new Array(0);
      this.campus = [];
      this.displayPagination = 'none';
      this.checkPreviousAndNext();
    } else {
      this.indexPagination = 0;
      this.displayPagination = 'inline-block';
      this.ngOnInit();
    }
  }

  openDelete(campus: Campus) {
    this.nameDelete = campus.name;
    this.idDelete = campus.id;
  }

  delete(idDelete: number) {
    this.campusService.delete(idDelete).subscribe(() => {
      this.ngOnInit();
      this.toastrService.success('Xóa thành công', 'Thông báo');
    });
  }

  openInfo(campus: Campus) {
    this.infoId = campus.employee.id;
    this.infoName = campus.employee.name;
    this.infoDate = campus.employee.birthDay;
    this.infoGender = campus.employee.gender;
    this.infoPlacement = campus.employee.placement.name;
  }
}
