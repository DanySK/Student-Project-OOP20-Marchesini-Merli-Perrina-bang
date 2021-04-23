package libs;

import java.util.ArrayList;
import java.util.List;

public class CircularList<X> extends ArrayList<X> {
    
    private static final long serialVersionUID = 7770028322718010907L;
    private int currentIndex = 0;
    
    public CircularList() { }
    
    public CircularList(List<X> list) {
        super(list);
    }
    
    /**
     * @return index of current element
     */
    public int getCurrentIndex() {
        return this.currentIndex;
    }
    
    /**
     * Sets index of current element
     * 
     * @param index
     */
    public void setCurrentIndex(final int index) {
        if(index < this.size()) {
            this.currentIndex = index;
        }
    }
    
    /**
     * @return current element
     */
    public X getCurrentElement() {
        return this.get(this.currentIndex);
    }
    
    /**
     * Sets current element
     * 
     * @param element
     */
    public void setCurrentElement(final X element) {
        if(this.contains(element)) {
            this.currentIndex = this.indexOf((X) element);
        }
    }
    
    /**
     * Changes the index of current element to the next one
     * 
     * @return the new current element
     */
    public X getNext() {
        if(this.currentIndex == this.size() - 1) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        return this.get(this.currentIndex);
    }
    
    /**
     * Gets next element of an input element
     * 
     * @param element
     * @return the element next to the one passed to the function
     */
    public X getNextOf(final X element) {
        int index;
        if(indexOf((X) element) == this.size() - 1) {
            index = 0;
        } else {
            index = currentIndex + 1;
        }
        return this.get(index);
    }
    
    /**
     * Changes the index of current element to the previous one
     * 
     * @return the new current element
     */
    public X getPrev() {
        if(this.currentIndex == 0) {
            currentIndex = this.size() - 1;
        } else {
            currentIndex--;
        }
        return this.get(this.currentIndex);
    }
    
    /**
     * Gets previous element of an input element
     * 
     * @param element
     * @return the element previous to the one passed to the function
     */
    public X getPrevOf(final X element) {
        int index;
        if(indexOf((X) element) == 0) {
            index = this.size() - 1;
        } else {
            index = currentIndex - 1;
        }
        return this.get(index);
    }
}