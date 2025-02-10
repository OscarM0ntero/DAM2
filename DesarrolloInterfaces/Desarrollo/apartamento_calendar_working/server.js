const express = require("express");
const axios = require("axios");
const cors = require("cors");

const app = express();
app.use(cors());

app.get("/api/ical", async (req, res) => {
    try {
        const response = await axios.get("https://ical.booking.com/v1/export?t=afbef337-0326-414c-8e9d-e40858b927d7", {
            headers: { "User-Agent": "Mozilla/5.0" } // Simula una solicitud desde un navegador
        });
        res.send(response.data);
    } catch (error) {
        res.status(500).send("Error al obtener el iCal");
    }
});

app.listen(3000, () => console.log("Servidor backend corriendo en http://localhost:3000"));
