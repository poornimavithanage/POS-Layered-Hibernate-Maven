package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Item;
import org.hibernate.Session;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    public String getLastItemCode() throws Exception {
        List list = session.createQuery("SELECT i.code FROM entity.Item i ORDER BY i.code DESC")
                .setMaxResults(1).list();
        return list.size() > 0 ? (String) list.get(0) : null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        return session.createQuery("FROM Item", Item.class).list();
    }

    @Override
    public Item find(String key) throws Exception {
        return session.find(Item.class, key);
    }

    @Override
    public void save(Item item) throws Exception {
        session.save(item);
    }

    @Override
    public void update(Item item) throws Exception {
        session.update(item);
    }

    @Override
    public void delete(String key) throws Exception {
        session.delete(session.load(Item.class, key));
    }
}
