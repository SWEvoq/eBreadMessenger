package swevoq.ebread.com.Chat.View.Chat;

import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import swevoq.ebread.com.Chat.Model.Chat.Message;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CustomIncomingMessageViewHolder.class)
public class CustomIncomingMessageViewHolderTest {
    @Mock
    View mockedView;

    @Mock
    Message mockedMessage;

    @Test
    public void onBind() throws Exception {
        CustomIncomingMessageViewHolder c=new CustomIncomingMessageViewHolder(mockedView);
        c.onBind(mockedMessage);
    }
}