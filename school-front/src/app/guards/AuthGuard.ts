import {inject} from "@angular/core";
import {CanActivateFn} from "@angular/router";
import {AuthorizationService} from "../services/authorization.service";

export const AuthGuard: CanActivateFn = (): boolean => {
    const authenticationService = inject(AuthorizationService);
    if (authenticationService.isLoggedIn()) {
        return true;
    }
    authenticationService.redirectToLoginPage();
    return false;
};