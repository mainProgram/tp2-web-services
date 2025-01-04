const { Eureka } = require("eureka-js-client");

module.exports = new Eureka({
    instance: {
        app: 'PROFESSEURS',
        instanceId: 'professeurs:8094',
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        statusPageUrl: 'http://localhost:8094/status',
        port: {
            '$': 8094,
            '@enabled': true,
        },
        vipAddress: '192.168.0.1',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        host: 'localhost',
        port: 8761,
        servicePath: '/eureka/apps/',
        maxRetries: 5,
        requestRetryDelay: 5000,
    },
});