package bstmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable, V> implements Map61B<K,V>
{
    //成员变量
    private BSTNode root;
    private int size;


    //辅助类
    private class BSTNode
    {
        pair pair;
        BSTNode left, right;

        //构造函数
        BSTNode(K key, V value)
        {
            this.pair = new pair(key, value);
            left = right = null;
        }
    }

    //辅助类
    public class pair
    {
        K key;
        V value;

        //构造函数
        pair(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }

    //迭代器类
    public class iterator implements Iterator<K>
    {
        private int pos;
        private ArrayList<K> keys;

        //构造函数
        public iterator()
        {
            BSTNode current = root;
            inorderTraversal(current);
        }

        //中序遍历
        void inorderTraversal (BSTNode current)
        {
            if (current != null)
            {
                inorderTraversal(current.left);
                keys.add(current.pair.key);
                inorderTraversal(current.right);
            }
        }

        public boolean hasNext()
        {
            return pos < keys.size();
        }

        public K next()
        {
            pos++;
            return keys.get(pos - 1);
        }
    }

    @Override
    public Iterator<K> iterator()
    {
        return new iterator();
    }

    //构造函数
    public BSTMap()
    {
        root = null;
        size = 0;
    }

    public void clear()
    {
        size = 0;
        root = null;
    }

    public boolean containsKey(K key)
    {
        BSTNode current = root;
        while (true)
        {
            if (current == null)
            {
                return false;
            }
            int result = key.compareTo(current.pair.key);
            if (result < 0)
            {
                current = current.left;
            }
            else if (result > 0)
            {
                current = current.right;
            }
            else
            {
                return true;
            }
        }
    }

    public V get(K key)
    {
        BSTNode current = root;
        while (true)
        {
            if (current == null)
            {
                return null;
            }
            int result = key.compareTo(current.pair.key);
            if (result < 0)
            {
                current = current.left;
            }
            else if (result > 0)
            {
                current = current.right;
            }
            else
            {
                return current.pair.value;
            }
        }
    }

    public int size()
    {
        return size;
    }

    public void put(K key, V value)
    {
        if (root == null)
        {
            root = new BSTNode(key, value);
            size++;
            return;
        }
        BSTNode current = root;
        BSTNode parent = null;
        int condition = 0;
        while (true)
        {
            if (current == null)
            {
                current = new BSTNode(key, value);
                if (condition == 0)
                    parent.left = current;
                else
                    parent.right = current;
                size++;
                return;
            }
            int result = key.compareTo(current.pair.key);
            if (result < 0)
            {
                parent = current;
                current = current.left;
                condition = 0;
            }
            else if (result > 0)
            {
                parent = current;
                current = current.right;
                condition = 1;
            }
            else
            {
                current.pair.value = value;
                return;
            }
        }
    }

    public Set<K> keySet()
    {
        Set<K> keys = new HashSet<K>();
        BSTNode current = root;
        inorderTraversal(current, keys);
        return keys;
    }

    private void inorderTraversal (BSTNode current, Set<K> keys)
    {
        if (current != null)
        {
            inorderTraversal(current.left, keys);
            keys.add(current.pair.key);
            inorderTraversal(current.right, keys);
        }
    }

    public V remove(K key)
    {
        V result = get(key);
        if (result == null)
            return null;
        common_remove(key);
        size--;
        return result;
    }

    public V remove(K key, V value)
    {
        V result = get(key);
        if (result == null || !result.equals(value))
            return null;
        common_remove(key);
        size--;
        return result;
    }

    private void common_remove(K key)
    {
        if (key.compareTo(root.pair.key) == 0 && size == 1)
        {
            root = null;
            return;
        }
        int condition = 0;
        BSTNode current = root;
        BSTNode prev = null;
        while (true)
        {
            int res = key.compareTo(current.pair.key);
            if (res < 0)
            {
                prev = current;
                current = current.left;
                condition = 0;
            }
            else if (res > 0)
            {
                prev = current;
                current = current.right;
                condition = 1;
            }
            else
                break;
        }
        if (current.left != null)
        {
            if (current.right == null)
            {
                singlechl(condition, current, prev);
                return;
            }
        }
        else if (current.right != null)
        {
            singlechl(condition, current, prev);
            return;
        }
        else
        {
            if (prev == null)
            {
                root = null;
                return;
            }
            else
            {
                if (condition == 0)
                    prev.left = null;
                else
                    prev.right = null;
                return;
            }
        }
        delete_swap(current, prev);
        delete_sink(current);
    }

    private void singlechl(int condition, BSTNode current, BSTNode prev)
    {
        BSTNode temp = current.left == null ? current.right : current.left;
        if (prev == null)
        {
            root = temp;
            return;
        }
        if (condition == 0)
            prev.left = temp;
        else
            prev.right = temp;

    }

    private void delete_swap(BSTNode current, BSTNode prev)
    {
        BSTNode temp = current.right;
        int condition = 0;
        while (temp.left != null)
        {
            prev = temp;
            temp = temp.left;
            condition = 1;
        }
        if (condition == 0)
        {
            if (prev == null)
            {
                temp.left = current.left;
                root = temp;
            }
            else
            {
                prev.right = temp;
                temp.left = current.left;
            }
        }
        else
        {
            prev.left = null;
            current.pair = temp.pair;
        }
    }

    private void delete_sink(BSTNode current)
    {
        while (true)
        {
            if (current.left != null && current.pair.key.compareTo(current.left.pair.key) < 0)
            {
                swap(current, current.left, 1);
                delete_sink(current.left);
            }
            else if (current.right != null && current.pair.key.compareTo(current.right.pair.key) > 0)
            {
                swap(current, current.right, 2);
                delete_sink(current.right);
            }
            else
                break;
        }
    }

    private void swap(BSTNode one, BSTNode two, int condition)
    {
        switch (condition)
        {
            case 1:
            {
                BSTNode right = two.right;
                BSTNode left = two.left;
                two.left = one;
                two.right = one.right;
                one.left = left;
                one.right = right;
                break;
            }
            case 2:
            {
                BSTNode right = two.right;
                BSTNode left = two.left;
                two.left = one.left;
                two.right = one;
                one.left = left;
                one.right = right;
                break;
            }
        }
    }
}
