import { Routes} from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { HikesComponent } from './pages/hikes/hikes.component';
import { RegisterComponent } from './pages/register/register.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { ContactComponent } from './pages/contact/contact.component';
import { ModelsComponent } from './pages/models/models.component';

export const routes: Routes = [
    {path:'home', component: HomeComponent},
    {path:'login', component: LoginComponent},
    {path:'logout', component: LogoutComponent},
    {path:'register', component: RegisterComponent},
    {path:'hikes', component: HikesComponent},
    {path:'models', component: ModelsComponent},
    {path:'contact', component: ContactComponent},

];
