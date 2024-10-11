import { Routes} from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { HikesComponent } from './pages/hikes/hikes.component';
import { RegisterComponent } from './pages/register/register.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { ContactComponent } from './pages/contact/contact.component';
import { ModelsComponent } from './pages/models/models.component';
import { authGuard } from './_helpers/auth.guard';

export const routes: Routes = [
    {path:'home', component: HomeComponent},
    {path:'login', component: LoginComponent},
    {path:'logout', component: LogoutComponent, canActivate: [authGuard]},
    {path:'register', component: RegisterComponent},
    {path:'hikes', component: HikesComponent, canActivate: [authGuard]},
    {path:'models', component: ModelsComponent, canActivate: [authGuard]},
    {path:'contact', component: ContactComponent},

];