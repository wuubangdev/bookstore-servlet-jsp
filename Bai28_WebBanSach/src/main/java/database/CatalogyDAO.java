package database;

import java.util.ArrayList;

import model.Catalogy;

public class CatalogyDAO implements DAOInterface<Catalogy> {

	ArrayList<Catalogy> data = new ArrayList<Catalogy>();

	public static CatalogyDAO getInstance() {
		return new CatalogyDAO();
	}

	@Override
	public int insert(Catalogy t) {
		Catalogy catalogy = this.selectById(t);
		if (catalogy == null) {
			data.add(catalogy);
			return 1;
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Catalogy> list) {
		int count = 0;
		for (Catalogy catalogy : list) {
			count += this.insert(catalogy);
		}

		return count;
	}

	@Override
	public Catalogy selectById(Catalogy t) {
		for (Catalogy catalogy : data) {
			if (catalogy.equals(t)) {
				return catalogy;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Catalogy> selectAll() {
		return this.data;
	}

	@Override
	public int update(Catalogy t) {
		Catalogy catalogy = this.selectById(t);
		if (catalogy!=null) {
			this.data.remove(catalogy);
			this.data.add(t);
			return 1;
		}
		
		return 0;
	}

	@Override
	public int delete(Catalogy t) {
		Catalogy catalogy = this.selectById(t);
		if (catalogy != null) {
			this.data.remove(catalogy);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Catalogy> list) {
		int count = 0;
		for (Catalogy catalogy : list) {
			count += this.delete(catalogy);
		}
		return count;
	}

}
