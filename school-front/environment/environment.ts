export const environment = {
    production: false,
    keycloak: {
        authority: 'http://localhost:8081',
        redirectUri: 'http://localhost:4200',
        postLogoutRedirectUri: 'http://localhost:4200/logout',
        realm: 'demo',
        clientId: 'myclient',
    },
    idleConfig: { idle: 10, timeout: 60, ping: 10 },
};