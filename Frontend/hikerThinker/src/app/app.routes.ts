import { Routes} from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { HikesComponent } from './pages/hikes/hikes.component';
import { RegisterComponent } from './pages/register/register.component';

export const routes: Routes = [
    {path:'home', component: HomeComponent},
    {path:'login', component: LoginComponent},
    {path:'hikes', component: HikesComponent},
    {path:'register', component: RegisterComponent},

];
