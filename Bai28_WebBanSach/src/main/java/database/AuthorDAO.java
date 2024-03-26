package database;

import java.util.ArrayList;

import model.Author;

public class AuthorDAO implements DAOInterface<Author> {

	ArrayList<Author> data = new ArrayList<Author>();

	public static AuthorDAO getInstance() {
		return new AuthorDAO();
	}

	@Override
	public int insert(Author t) {
		Author author = this.selectById(t);
		if (author == null) {
			data.add(author);
			return 1;
		}
		return 0;
	}

	@Override
	public int insertAll(ArrayList<Author> list) {
		int count = 0;
		for (Author author : list) {
			count += this.insert(author);
		}

		return count;
	}

	@Override
	public Author selectById(Author t) {
		for (Author author : data) {
			if (author.equals(t)) {
				return author;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Author> selectAll() {
		return this.data;
	}

	@Override
	public int update(Author t) {
		Author author = this.selectById(t);
		if (author!=null) {
			this.data.remove(author);
			this.data.add(t);
			return 1;
		}
		
		return 0;
	}

	@Override
	public int delete(Author t) {
		Author author = this.selectById(t);
		if (author != null) {
			this.data.remove(author);
			return 1;
		}
		return 0;
	}

	@Override
	public int deleteAll(ArrayList<Author> list) {
		int count = 0;
		for (Author author : list) {
			count += this.delete(author);
		}
		return count;
	}

}
