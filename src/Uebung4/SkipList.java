package Uebung4;

/**
 * Skip-List with public SListItem
 * 
 * @author Christoph Stamm, Alain Remund
 *
 * @param <E> Data element type.
 */
public class SkipList<E> {
	public static class SListItem<E> {
		private SListItem<E>[] m_next;
		private int m_key;
		private E m_data;

		@SuppressWarnings("unchecked")
		private SListItem(int key, E data, int level) {
			m_key = key;
			m_data = data;
			m_next = new SListItem[level + 1];
		}

		public SListItem<E> getNext(int level) {
			if (level < m_next.length) {
				return m_next[level];
			} else {
				return null;
			}
		}

		private void setNext(int level, SListItem<E> elem) {
			assert level < m_next.length : "wrong level: " + level;
			m_next[level] = elem;
		}

		public int getLevel() {
			return m_next.length - 1;
		}

		public E getData() {
			return m_data;
		}

		public void setData(E m_data) {
			this.m_data = m_data;
		}

		public int getKey() {
			return m_key;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	private static final double PROBABILITY = 0.5;
	public static final int HEADER_KEY = Integer.MIN_VALUE;
	public static final int TAIL_KEY = Integer.MAX_VALUE;
	public static final Object NOT_FOUND = null;
	
	private SListItem<E> m_head; 	// smallest key, level = MaxLevel
	private SListItem<E> m_tail;	// largest key, level = MaxLevel
	private int m_maxLevel;			// MaxLevel
	private int m_size;				// number of element in the list

	/**
	 * Creates new skip-list
	 * @param maxLevel maximum number of levels
	 */
	public SkipList(int maxLevel) {
		m_maxLevel = maxLevel;
		m_head = new SListItem<E>(HEADER_KEY, null, m_maxLevel);
		m_tail = new SListItem<E>(TAIL_KEY, null, m_maxLevel);
		for (int i = 0; i <= m_maxLevel; i++) {
			m_head.setNext(i, m_tail);
		}
	}

	/**
	 * Return number of list elements.
	 * @return
	 */
	public int size() {
		return m_size;
	}
	
	/**
	 * Searchs given key: O(log n)
	 * @param key key looking for
	 * @return data associated with key or NOT_FOUND
	 */
	@SuppressWarnings("unchecked")
	public E search(int key) {
		SListItem<E> aktuell = m_head;
		for (int i = m_maxLevel; i >= 0; i--) {
			while (aktuell.getNext(i).getKey() < key) {
				aktuell = aktuell.getNext(i);
			}
		}
		aktuell = aktuell.getNext(0);
		if (aktuell.getKey() == key) {
			return aktuell.getData();
		} else {
			return (E)NOT_FOUND;
		}
	}

	/**
	 * Adds new key-data pair to list. If the list already contains the given key, then the data is updated.
	 * O(log n)
	 * @param key key of data to be added
	 * @param data data to be added
	 */
	public void add(int key, E data) {
		@SuppressWarnings("unchecked")
		SListItem<E>[] update = new SListItem[m_maxLevel + 1];
		SListItem<E> aktuell = m_head;
		for (int i = m_maxLevel; i >= 0; i--) {
			while (aktuell.getNext(i).getKey() < key) {
				aktuell = aktuell.getNext(i);
			}
			update[i] = aktuell;
		}
		aktuell = aktuell.getNext(0);
		if (aktuell.getKey() == key) {
			aktuell.setData(data);
		} else {
			SListItem<E> neuesSListItem = new SListItem<E>(key, data, randomLevel());
			for (int i = 0; i <= neuesSListItem.getLevel(); i++) {
				neuesSListItem.setNext(i, update[i].getNext(i));
				update[i].setNext(i, neuesSListItem);
			}
			m_size++;
		}
	}

	/**
	 * Removes data associated with key from the list: O(log n)
	 * @param key key to be removed
	 * @return data of the removed element or NOT_FOUND if key has not been found in the list
	 */
	public E remove(int key) {
            //find
            {
                SListItem<E> bevoere = m_head;
                E returnValue = null;
                for (int i = m_maxLevel; i >= 0; i--) {
                    while(bevoere.getNext(i).m_key<key)
                    {
                        bevoere=bevoere.getNext(i);
                    }
                    if (bevoere.getNext(i).m_key==key) {
                        if(returnValue==null)
                        {
                           returnValue= bevoere.getNext(i).m_data;
                        }
                        bevoere.setNext(i, bevoere.getNext(i).getNext(i));
                    }
                }
                
                //test
                this.search(key);
                return returnValue;
                
            }
	}

	/**
	 * Merges two skip-lists and returns a new list
	 * @param liste1
	 * @param liste2
	 * @return new skip-list containing the union of the two given lists
	 */
	public static <E> SkipList<E> merge(SkipList<E> liste1, SkipList<E> liste2) {
		return null;
	}
	
	/**
	 * Compute a random level
	 * @return 0 <= level <= MaxLevel
	 */
	public int randomLevel() {
		int level = 0;
		while (level < m_maxLevel && Math.random() < PROBABILITY) {
			level++;
		}
		return level;
	}

	/**
	 * Return header
	 * @return list header
	 */
	public SListItem<E> getHeader() {
		return m_head;
	}

	/**
	 * Return MaxLevel
	 * @return MaxLevel
	 */
	public int getMaxLevel() {
		return m_maxLevel;
	}

	public String getProbability() {
		return String.valueOf(PROBABILITY);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder('[');
		SListItem<E> aktuell = m_head.getNext(0);
		
		while (aktuell != m_tail) {
			sb.append('(').append(aktuell.getKey()).append(',').append(aktuell.getData()).append(')');
			aktuell = aktuell.getNext(0);
			if (aktuell != m_tail) sb.append(',');
		}
		sb.append(']');
		return sb.toString();
	}

}
