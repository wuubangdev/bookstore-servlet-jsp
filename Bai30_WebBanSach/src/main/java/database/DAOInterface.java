package database;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert(T t);

	public int insertAll(ArrayList<T> list);
	
	public T selectById(T t);
	
	public ArrayList<T> selectAll();
	
	public int update(T t);

	public int delete(T t);

	public int deleteAll(ArrayList<T> list);
}
