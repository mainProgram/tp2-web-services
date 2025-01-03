const express = require('express');
const { sequelize } = require('./models');
const professeurRoutes = require('./routes/professeur.routes');

const app = express();

// Middleware pour parser le JSON
app.use(express.json());

// Synchroniser la base de données
sequelize.sync({ force: true }).then(() => {
    console.log('Base de données synchronisée');
});

// Routes
app.use('/api/v1', professeurRoutes);

// Démarrage du serveur
const port = 3000;
app.listen(port, () => {
    console.log(`Serveur démarré sur le port ${port}`);
});