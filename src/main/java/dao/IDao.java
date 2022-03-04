package dao;

import java.util.List;

public interface IDao<T> {

	//CRUD - C
	public boolean create(T object);
	
	// CRUD - R
	
	public List read();
}
