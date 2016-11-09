SELECT `nb_partant`,`nb_chv_inf_cinq`, `rap_gagnant`, `rap_place_premier`, `rap_place_deuxieme`,`rap_place_troisieme`,`num_chv_premier`,`num_chv_deuxieme`,
`num_chv_troisieme`,`total_pourcent`,`pourcent_fav_premier`,`num_fav_premier`,`nom_chv_premier`,
`musique_premier`,`gains_chv_premier`,`pourcent_fav_deuxieme`,
`num_fav_deuxieme`,`pourcent_fav_troisieme`,`num_fav_troisieme`,`nb_chv_fav_place`,`date`,
`num_reunion`,`num_course`,`hippodrome`,`prime`,`type_course`,`agesexe_chvl_premier`,`depart`
FROM `coursecomplete`
WHERE 
  `nb_partant` > 7
AND
   ( `type_course` = 'Attel�' OR `type_course` = 'Mont�' )
ORDER BY
  `date`,`num_reunion`, `hippodrome`, `num_course` ASC