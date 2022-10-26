import {Component, OnInit} from '@angular/core';
import {Customer} from "../customer";
import {CustomerService} from "../customer.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customer: Customer[] = [];
  number: number;
  indexPagination = 0;
  totalPage: Array<number>;
  previousPageStyle = 'inline-block';
  nextPageStyle = 'inline-block';
  totalElements = 0;
  pageSize: number;
  displayPagination = 'inline-block';
  numberOfElement = 0;
  keyword = '';
  nameDelete: string;
  idDelete: number;

  constructor(private customerService: CustomerService,
              private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.customerService.findAll(this.indexPagination, this.keyword, this.pageSize).subscribe((result?: any) => {
      if (result === null) {
        this.totalPage = new Array(0);
        this.customer = [];
        this.displayPagination = 'none';
      } else {
        this.number = result?.number;
        this.pageSize = result?.size;
        this.numberOfElement = result?.numberOfElements;
        this.customer = result.content;
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
    if (this.checkRegex(this.keyword)) {
      this.indexPagination = 0;
      this.totalPage = new Array(0);
      this.customer = [];
      this.displayPagination = 'none';
      this.checkPreviousAndNext();
    } else {
      this.indexPagination = 0;
      this.displayPagination = 'inline-block';
      this.ngOnInit();
    }
  }

  openDelete(customer: Customer) {
    this.nameDelete = customer.name;
    this.idDelete = customer.id;
  }

  delete(idDelete: number) {
    this.customerService.delete(idDelete).subscribe(() => {
      this.ngOnInit();
      this.toastrService.success('Xóa thành công', 'Thông báo');
    });
  }
}
