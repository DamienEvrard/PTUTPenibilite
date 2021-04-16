INSERT INTO PIECE (id, libelle) VALUES
    (1, 'Salle de réunion'),
    (2, 'Salle de travail'),
    (3, 'Bureau du PDG');



INSERT INTO TYPECAPTEUR (id, libelle,limite_Max,limite_Min) VALUES
    (1, 'Température','-30','50'),
    (2, 'Bruit','180','0'),
    (3, 'Vibration','15000','0');



INSERT INTO CAPTEUR (id, libelle,frequence_Mesure,salle_id,type_id) VALUES
    (1, 'Thermomètre','60',1,1),
    (2, 'Dosimètre','120',1,2),
    (3, 'Vibromètre','420',1,3),

    (4, 'Thermomètre','60',2,1),
    (5, 'Dosimètre','120',2,2),
    (6, 'Vibromètre','420',2,3),

    (7, 'Thermomètre','60',3,1),
    (8, 'Dosimètre','120',3,2),
    (9, 'Vibromètre','420',3,3);



INSERT INTO MESURE (id,date,valeur,capteur_id) VALUES  
    (01, '2021-03-27 09:30:00','18',1),
    (02, '2021-03-27 10:30:00','20',1),
    (03, '2021-03-28 11:30:00','22',4),
    (04, '2021-03-28 12:30:00','24',4),
    (05, '2021-03-29 8:30:00','16',7),
    (06, '2021-03-29 9:30:00','18',7),

    (08, '2021-03-29 9:20:00','67',8),
    (09, '2021-03-29 11:20:00','90',5),
    (10, '2021-03-29 14:20:00','85',2),
    (11, '2021-03-28 13:30:00','105',5),
    (12, '2021-03-28 15:30:00','80',2),
    (13, '2021-03-28 17:30:00','100',5),
    (14, '2021-03-28 18:30:00','60',8),

    (15, '2021-03-29 16:20:00','456',3),
    (16, '2021-03-30 17:20:00','764',3),
    (17, '2021-03-31 16:20:00','168',9),
    (18, '2021-04-21 17:20:00','886',6),
    (19, '2021-04-02 16:20:00','336',9),
    (20, '2021-04-03 17:20:00','852',6);