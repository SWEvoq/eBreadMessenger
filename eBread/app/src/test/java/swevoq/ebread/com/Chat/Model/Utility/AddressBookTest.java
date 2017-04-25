package swevoq.ebread.com.Chat.Model.Utility;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AddressBookTest {
    @Mock
    View mockedView;
    @Mock
    ViewGroup mockedViewGroup;
    @Mock
    Context mockedContext;

    @Test
    public void getView() throws Exception {
        AddressBook a=new AddressBook(mockedContext,1,new ArrayList<String>());
        View expected=mockedView;
        View actual=a.getView(1,mockedView,mockedViewGroup);
        assertEquals(expected,actual);
    }

    @Test
    public void remove() throws Exception {
        List<String> list=new ArrayList<String>();
        list.add("string");
        AddressBook a=new AddressBook(mockedContext,1,list);
        a.remove("string");
    }

    @Test
    public void toggleSelection() throws Exception {
        AddressBook a=new AddressBook(mockedContext,1,new ArrayList<String>());
        a.toggleSelection(1);
    }

    @Test
    public void selectView() throws Exception {
        AddressBook a=new AddressBook(mockedContext,1,new ArrayList<String>());
        a.selectView(1,true);
    }

    @Test
    public void getSelectedItemsIds() throws Exception {
        AddressBook a=new AddressBook(mockedContext,1,new ArrayList<String>());
        SparseBooleanArray s=a.getSelectedItemsIds();
        assertEquals(s instanceof SparseBooleanArray,true);
    }
}