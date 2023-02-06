import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './search/search.component'
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
 {
    path: '',
    redirectTo: 'search',
    pathMatch: 'full'
  },
/*   {
    path: 'home',
    loadChildren: () => import('./home/home.module').then( m => m.HomePageModule)
  },*/
  {
    path: 'search', component: SearchComponent
  },
  {
    path: 'info', component: HomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
