import {Employee} from "./employee";

export interface Campus {
  id?: number;
  name?: string;
  date?: string;
  address?: string;
  isDeleted?: boolean;
  employee?: Employee
}
