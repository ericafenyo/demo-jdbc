package fr.diginamic.jdbc.recensement.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * A generic data access object (DAO) interface for managing entities.
 *
 * @param <T> The type of entity handled by the DAO.
 */
public interface Dao<T> {

  /**
   * Inserts a list of entities into the database in bulk.
   *
   * @param entities A list of entities to be inserted.
   * @return An array of integers indicating the number of rows affected by each insertion.
   * @throws SQLException If a database access error occurs or the execution fails.
   */
  int[] bulkInsert(List<T> entities) throws SQLException;

  /**
   * Retrieves a list of entities from the database.
   *
   * @return A list of entities retrieved from the database.
   * @throws SQLException If a database access error occurs or the retrieval fails.
   */
  List<T> find() throws SQLException;

  /**
   * Reduces the data from the database to a map for efficient lookup.
   *
   * @return A map where keys are strings and values are integers for efficient data retrieval.
   * @throws SQLException If a database access error occurs or the reduction fails.
   */
  Map<String, Integer> reduce() throws SQLException;
}
