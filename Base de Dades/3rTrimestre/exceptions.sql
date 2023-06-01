-- Exercice 1
CREATE OR REPLACE PROCEDURE update_stadium_capacities AS
  CURSOR c_clubs IS
    SELECT club_name, stadium_capacity, member_count
    FROM clubs;
    
  v_new_capacity NUMBER;
BEGIN
  FOR club_rec IN c_clubs LOOP
    -- Calculamos la nueva capacidad del estadio
    v_new_capacity := club_rec.member_count * 1.3;
    
    -- Nos aseguramos que como minimo sea de 10000
    IF v_new_capacity < 10000 THEN
      v_new_capacity := 10000;
    END IF;
    
    -- Actualizamos su tamanyo
    UPDATE clubs
    SET stadium_capacity = v_new_capacity
    WHERE club_name = club_rec.club_name;
  END LOOP;
  
  DBMS_OUTPUT.PUT_LINE('Stadium capacities updated successfully.');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;

-- Exercice 2
CREATE OR REPLACE PROCEDURE delete_team(p_team_name IN clubs.club_name%TYPE) AS
  CURSOR c_players(p_team_name IN clubs.club_name%TYPE) IS
    SELECT player_id
    FROM players
    WHERE club_name = p_team_name
    FOR UPDATE;
    
  CURSOR c_matches(p_team_name IN clubs.club_name%TYPE) IS
    SELECT match_id
    FROM matches
    WHERE home_team = p_team_name OR away_team = p_team_name
    FOR UPDATE;
BEGIN
  -- Eliminar los jugadores del equipo
  FOR player_rec IN c_players(p_team_name) LOOP
    DELETE FROM players
    WHERE player_id = player_rec.player_id;
  END LOOP;
  
  -- Eliminar los partidos relacionados con el equipo
  FOR match_rec IN c_matches(p_team_name) LOOP
    DELETE FROM matches
    WHERE match_id = match_rec.match_id;
  END LOOP;
  
  -- Eliminar el equipo de la tabla
  DELETE FROM clubs
  WHERE club_name = p_team_name;
  
  DBMS_OUTPUT.PUT_LINE('Team ' || p_team_name || ' deleted successfully.');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END;

-- Exercice 3 
CREATE OR REPLACE PROCEDURE list_staff_names AS
BEGIN
  FOR staff_rec IN (SELECT staff_name, supervisor FROM staff) LOOP
    IF staff_rec.supervisor IS NULL THEN
      RAISE_APPLICATION_ERROR(-20000, 'Without supervisor: ' || staff_rec.staff_name);
    ELSE
      RAISE_APPLICATION_ERROR(-20001, 'With supervisor: ' || staff_rec.staff_name);
    END IF;
  END LOOP;
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error code: ' || SQLCODE || ', Error message: ' || SQLERRM);
END;