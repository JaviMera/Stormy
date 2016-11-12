package javier.com.stormy;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javier.com.stormy.fragments.ForecastCurrentFragmentView;
import javier.com.stormy.fragments.ForecastCurrentFragmentPresenter;

/**
 * Created by Javi on 11/11/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ForecastCurrentFragmentPresenterTest {

    private ForecastCurrentFragmentPresenter mPresenter;

    @Mock
    public ForecastCurrentFragmentView mView;

    @Before
    public void setUp() throws Exception {

        mPresenter = new ForecastCurrentFragmentPresenter(mView);
    }

    @Test
    public void setLocationTextView() throws Exception {

        // Arrange
        String timezone = "Johnson, check Allepo";

        // Act
        mPresenter.setLocationTextView(timezone);

        // Assert
        Mockito.verify(mView).setLocationTextView(timezone);
    }

    @Test
    public void setTemperatureTextView() throws Exception {

        // Arrange
        double temperature = -12312;

        // Act
        mPresenter.setTemperatureTextView(temperature);

        // Assert
        Mockito.verify(mView).setTemperatureTextView(temperature);
    }

    @Test
    public void setTimeTextView() throws Exception {

        // Arrange
        String time = "0:00 am";

        // Act
        mPresenter.setTimeTextView(time);

        // Assert
        Mockito.verify(mView).setTimeTextView(time);
    }

    @Test
    public void setHumidityTextView() throws Exception {

        // Arrange
        double humidity = 0.32;

        // Act
        mPresenter.setHumidityTextView(humidity);

        // Assert
        Mockito.verify(mView).setHumidity(humidity);
    }

    @Test
    public void setPrecipTextView() throws Exception {

        // Arrange
        int precip = 21;

        // Act
        mPresenter.setPrecipitationTextView(precip);

        // Assert
        Mockito.verify(mView).setPrecipitationTextView(precip);
    }

    @Test
    public void setSummaryTextView() throws Exception {

        // Arrange
        String summary = "mega cold";

        // Act
        mPresenter.setSummaryTextView(summary);

        // Assert
        Mockito.verify(mView).setSummaryTextView(summary);
    }

    @Test
    public void setIconImageView() throws Exception {

        // Arrange
        int iconId = 1234;

        // Act
        mPresenter.setIconImageView(iconId);

        // Assert
        Mockito.verify(mView).setIconImageView(iconId);
    }
}
