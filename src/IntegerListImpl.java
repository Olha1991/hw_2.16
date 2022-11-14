import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private static final int INITIAL_SIZE = 15;
    private Integer[] data;
    private  int capacity;

    public IntegerListImpl(){
        this(INITIAL_SIZE);
    }

    public IntegerListImpl(int a){
        if(a <= 0){
            throw new IllegalArgumentException("Size must be positive!");
        }
        data = new Integer[a];
        capacity = 0;
    }

    private void grow(){
        Integer[] newData = new Integer[(int) (1.5 * data.length)];
        System.arraycopy(data, 0, newData, 0, capacity);
        data = newData;
    }

    @Override
    public Integer add(Integer item){
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item){
        if (capacity >= data.length){
           grow();
        }
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkResult(index, false);
        System.arraycopy(data, index, data, index + 1, capacity - index);
        data[index] = item;
        capacity++;
        return item;
    }

    private void checkNonNegativeIndex(int index) {
        if(index < 0){
            throw new IllegalArgumentException("Can't be store null in a list.");
        }
    }

    private void checkNotNull(Integer item) {
        if(Objects.isNull(item)){
            throw new IllegalArgumentException("Index must not be negative.");
        }
    }

    private void checkResult(int index, boolean includeEquality){
        boolean expression = includeEquality ? index >= capacity : index > capacity;
        if(expression){
            throw new IllegalArgumentException("Index: " + index + ". Size: " + capacity);
        }
    }

    @Override
    public Integer set(int index, Integer item){
        checkNotNull(item);
        checkNonNegativeIndex(index);
        checkResult(index, true);
        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item){
        int indexRemoving = indexOf(item);
        if(indexRemoving == -1){
            throw new IllegalArgumentException("Element not found!");
        }
        return remove(indexRemoving);
    }

    @Override
    public Integer remove(int index){
        checkNonNegativeIndex(index);
        checkResult(index, true);
        Integer removed = data[index];
        System.arraycopy(data, index + 1, data, index + 1, capacity - index - 1);
        data[--capacity] = null;
        return removed;
    }


    @Override
    public int indexOf(Integer item){
        checkNotNull(item);
        int index = -1;
        for(int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item){
        checkNotNull(item);
        int index = -1;
        for(int i = capacity - 1; i >= 0; i--){
            if(data[i].equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index){
        checkNonNegativeIndex(index);
        checkResult(index, true);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList){
        if(size() != otherList.size()){
            return false;
        }
        for(int i = 0; i < capacity; i++){
            if(!data[i].equals(otherList.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size(){
        return capacity;
    }

    @Override
    public boolean isEmpty(){
        return size() ==0;
    }

    @Override
    public void clear(){
        for(int i = 0; i < capacity; i++){
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        System.arraycopy(data, 0, result, 0, capacity);
        return result;
    }

    public void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        Integer pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, end);
        return i + 1;
    }
    private  void swap(Integer[] arr, int left, int right) {
        Integer temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    @Override
    public boolean contains(Integer item){
        checkNotNull(item);
        Integer[] arrayForSearch = toArray();
        quickSort(arrayForSearch, 0, arrayForSearch.length - 1);
        int min = 0;
        int max = arrayForSearch.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if(item.equals(arrayForSearch[mid])){
                return true;
            }
            if(item < arrayForSearch[mid]){
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }
        return false;
    }
}

