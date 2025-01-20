import {Component, DestroyRef, OnInit} from '@angular/core';
import {RouterModule, RouterOutlet} from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {CommonModule} from '@angular/common';
import {MatNativeDateModule} from '@angular/material/core';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatAnchor} from "@angular/material/button";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";
import {AuthorizationService} from "./services/authorization.service";
import {UserIdleService} from "angular-user-idle";

@Component({
  selector: 'app-root',
    imports: [
        CommonModule,
        RouterOutlet,
        MatIconModule,
        RouterModule,
        MatNativeDateModule,
        MatToolbarModule,
        MatAnchor
    ],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent implements OnInit{
  title = 'School-App';
    private destroyRef!: DestroyRef;

    constructor(
        private readonly authenticationService: AuthorizationService,
        private readonly userIdleService: UserIdleService,
    ) {}

    ngOnInit(): void {
        if (this.authenticationService.isLoggedIn()) {
            this.userIdleService.startWatching();
            this.userIdleService
                .onTimerStart()
                .pipe(takeUntilDestroyed(this.destroyRef))
                .subscribe();
            this.userIdleService
                .onTimeout()
                .pipe(takeUntilDestroyed(this.destroyRef))
                .subscribe(() => {
                    alert('Your session has timed out. Please log in again.');
                    this.authenticationService.logout();
                    this.userIdleService.resetTimer();
                });
        }
    }
}
