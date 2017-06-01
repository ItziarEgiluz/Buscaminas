package packBuscaminas;
import java.util.*;

public class ListaOrdenada<T> implements List<T> {
	private ArrayList<T> datos;
	private Comparator<T> comparador;
	
	 public ListaOrdenada (Comparator<T> com){
	    	datos = new ArrayList<T>();
	    	comparador = com;
	    }
	    
	    public ListaOrdenada (Collection <? extends T> colum, Comparator<T> com){
	    	for( T elem : colum ){
	    		datos.add( elem );
	    	}
	    	comparador =  com;
	    }	
	    
	    public boolean add(T element){
	    	if ( datos.isEmpty() ){
	    		return datos.add(element);
	    	}
	    	else{
	    		int i = 0;
	    		while( i < datos.size() && comparador.compare( datos.get(i) , element ) < 0   ){
	    			i++;
	    		}
	    		datos.add(i , element);
	    		return true;
	    	}
	    }
	    
	    public void setComparator(Comparator<T> com){
	    	comparador = com;
	    }
	    
	    public int binarySearch(T element)  {
	    	int high = datos.size() - 1;
	    	int low = 0;

	    	while( high >= low ){
	    		int middle = ( low + high ) / 2;
	    		if ( datos.get(middle).equals( element ) ){
	    			return middle;
	    		}
	    		if ( comparador.compare( datos.get(middle) , element ) < 0 ){
	    			low = middle+1;
	    		}
	    		
	    		if ( comparador.compare( datos.get(middle) , element ) > 0 ){
	    			high = middle - 1;
	    		}
	    	}
	    	
	    	return -1;
	    }

	    public String toString(){
	    	return datos.toString();
	    }
	    
		@Override
		public void add(int arg0, T arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean addAll(Collection<? extends T> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int arg0, Collection<? extends T> arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean contains(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T get(int arg0) {
			// TODO Auto-generated method stub
			return datos.get(arg0);
		}

		@Override
		public int indexOf(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<T> iterator() {
			// TODO Auto-generated method stub
			return datos.iterator();
		}

		@Override
		public int lastIndexOf(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ListIterator<T> listIterator() {
			// TODO Auto-generated method stub
			return datos.listIterator();
		}

		@Override
		public ListIterator<T> listIterator(int arg0) {
			// TODO Auto-generated method stub
			return datos.listIterator(arg0);
		}

		@Override
		public boolean remove(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T remove(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean removeAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T set(int arg0, T arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return datos.size();
		}

		@Override
		public List<T> subList(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] arg0) {
			// TODO Auto-generated method stub
			return null;
		}
}
