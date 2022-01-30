import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {AdminPanelService} from "../services/admin-panel.service";
import {Roles} from "../models/Roles";
import {User} from "../models/user";
import {MatTableDataSource} from "@angular/material/table";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import {MatSort, Sort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'surname','details'];
  usersList:User[] =[];
  rolesList:Roles[] = [];
  // @ts-ignore
  dataSource: MatTableDataSource<User>;
  // @ts-ignore
  @ViewChild(MatSort) sort: MatSort;
  // @ts-ignore
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private adminService:AdminPanelService,
              private _liveAnnouncer: LiveAnnouncer) {}

  ngOnInit(): void {
    this.adminService.getRoles().then(result => {
      // @ts-ignore
      this.rolesList = result;
    });
  }

  displayUsers(id:number){
    this.adminService.getUsersWithRole(id).then(result => {
      // @ts-ignore
      this.usersList = result;
      this.dataSource = new MatTableDataSource<User>(this.usersList);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event){
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
