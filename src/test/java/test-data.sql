INSERT INTO PIECE (id, libelle) VALUES
    (1, 'Salle de réunion');
    (2, 'Salle de travail');
    (3, 'Bureau du PDG');



INSERT INTO TYPECAPTEUR (id, libelle,limiteMax,limiteMin) VALUES
    (1, 'Température','-30°','50°');
    (2, 'Bruit','180 dB','0 dB');
    (3, 'Vibration','15000 Hz','0 Hz');



INSERT INTO CAPTEUR (id, libelle,frequenceMesure,salle_id,type_id) VALUES
    (1, 'Thermomètre','60',1,1);
    (2, 'Dosimètre','120',1,2);
    (3, 'Vibromètre','420',1,3);

    (4, 'Thermomètre','60',2,1);
    (5, 'Dosimètre','120',2,2);
    (6, 'Vibromètre','420',2,3);

    (7, 'Thermomètre','60',3,1);
    (8, 'Dosimètre','120',3,2);
    (9, 'Vibromètre','420',3,3);



INSERT INTO MESURE (id,dates,valeur,capteur_id) VALUES  
    (01, '27/03/21 09:30','18°',1);
    (02, '28/03/21 10:30','20°',1);
    (03, '28/03/21 11:30','22°',4);
    (04, '28/03/21 12:30','24°',4);
    (05, '29/03/21 8:30','16°',7);
    (06, '29/03/21 9:30','18°',7);

    (08, '29/03/21 9:20','67 dB',8);
    (09, '29/03/21 11:20','90 dB',5);
    (10, '29/03/21 14:20','85 dB',2);
    (11, '28/03/21 13:30','105 dB',5);
    (12, '28/03/21 15:30','80 dB',2);
    (13, '28/03/21 17:30','100 dB',5);
    (14, '28/03/21 18:30','60 dB',8);

    (15, '29/03/21 16:20','456 Hz',3);
    (16, '30/03/21 17:20','764 Hz',3);
    (17, '31/03/21 16:20','168 Hz',9);
    (18, '01/04/21 17:20','886 Hz',6);
    (19, '02/04/21 16:20','336 Hz',9);
    (20, '03/04/21 17:20','852 Hz',6);