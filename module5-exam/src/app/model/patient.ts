import {Patienter} from './patienter';

export interface Patient {
  id?: number;
  patienter?: Patienter;
  dayIn?: string;
  dayOut?: string;
  reason?: string;
  method?: string;
  doctor?: string;
}
