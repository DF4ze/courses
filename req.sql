SELECT 
`nb_partant`, 
`nb_chv_inf_cinq`
, `rap_gagnant`
, `rap_place_premier`
, `rap_place_deuxieme`
,`rap_place_troisieme`
,`num_chv_premier`
,`num_chv_deuxieme`,
`num_chv_troisieme`
,`total_pourcent`
,`pourcent_fav_premier`
,`num_fav_premier`
,`nom_chv_premier`,
`musique_premier`
,`gains_chv_premier`
,`pourcent_fav_deuxieme`,
`num_fav_deuxieme`
,`pourcent_fav_troisieme`
,`num_fav_troisieme`
,`nb_chv_fav_place`
,`date`,
`num_reunion`
,`num_course`
,`hippodrome`
,`prime`
,`type_course`
,`agesexe_chvl_premier`
,`depart`,
`1er`,`2eme`,`3eme`,`4eme`,`5eme`,`6eme`,`7eme`,`8eme`,`9eme`,`10eme`,`11eme`,`12eme`,`13eme`,`14eme`,`15eme` 
FROM `coursecomplete`
WHERE 
  `nb_partant` > 7
AND
   ( `type_course` = 'Attelé' OR `type_course` = 'Monté' )
ORDER BY
  `date`,`num_reunion`, `hippodrome`, `num_course` ASC