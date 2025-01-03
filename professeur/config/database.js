const Sequelize = require('sequelize');

module.exports = new Sequelize('professeur', 'root', 'root', {
    host: 'localhost',
    dialect: 'mysql',
});