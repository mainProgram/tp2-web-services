const express = require('express');
const router = express.Router();
const {Professeur} = require("../models");

// CREATE - Ajouter un nouveau professeur
router.post('/professeurs', async (req, res) => {
    try {
        const professeur = await Professeur.create({
            nom: req.body.nom,
            prenom: req.body.prenom,
            specialite: req.body.specialite,
            telephone: req.body.telephone
        });
        res.status(201).json(professeur);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// READ - Obtenir tous les professeurs
router.get('/professeurs', async (req, res) => {
    try {
        const professeurs = await Professeur.findAll();
        res.json(professeurs);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// READ - Obtenir un professeur par ID
router.get('/professeurs/:id', async (req, res) => {
    try {
        const professeur = await Professeur.findByPk(req.params.id);
        if (professeur) {
            res.json(professeur);
        } else {
            res.status(404).json({ message: 'Professeur non trouvé' });
        }
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// UPDATE - Modifier un professeur
router.put('/professeurs/:id', async (req, res) => {
    try {
        const professeur = await Professeur.findByPk(req.params.id);
        if (professeur) {
            await professeur.update({
                nom: req.body.nom || professeur.nom,
                prenom: req.body.prenom || professeur.prenom,
                specialite: req.body.specialite || professeur.specialite,
                telephone: req.body.telephone || professeur.telephone
            });
            res.json(professeur);
        } else {
            res.status(404).json({ message: 'Professeur non trouvé' });
        }
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

// DELETE - Supprimer un professeur
router.delete('/professeurs/:id', async (req, res) => {
    try {
        const professeur = await Professeur.findByPk(req.params.id);
        if (professeur) {
            await professeur.destroy();
            res.json({ message: 'Professeur supprimé' });
        } else {
            res.status(404).json({ message: 'Professeur non trouvé' });
        }
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

module.exports = router;
