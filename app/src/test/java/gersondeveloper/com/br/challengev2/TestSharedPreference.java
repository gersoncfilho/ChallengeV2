package gersondeveloper.com.br.challengev2;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import gersondeveloper.com.br.challengev2.Model.SharedPreferenceEntry;
import gersondeveloper.com.br.challengev2.Util.SharedPreferenceHelper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by gerso on 18/10/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestSharedPreference {
    private static final String USERNAME_TEST = "gersoncfilho";
    private static final String NAME_TEST = "Gerson";
    private static final String LAST_NAME_TEST = "Cardoso";
    private static final String EMAIL_TEST = "gersoncfilho@mac.com";
    private static final String PHONE_TEST = "99545468";


    private SharedPreferenceEntry sharedPreferenceEntry;


    private SharedPreferenceHelper mockSharedPreferencesHelper;

    private SharedPreferenceHelper mockBrokenSharedPreferencesHelper;

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences mockBrokenSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Mock
    SharedPreferences.Editor mockBrokenEditor;

    @Before
    public void initMocks()
    {
        //SharedPreference to persist
        sharedPreferenceEntry = new SharedPreferenceEntry(USERNAME_TEST, NAME_TEST, LAST_NAME_TEST, EMAIL_TEST, PHONE_TEST);

        mockSharedPreferencesHelper = createMockSharedPreference();

        mockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference();
    }

    @Test
    public void sharedPreferencesHelper_SaveAndReadPersonalInformation() {
        // Save the personal information to SharedPreferences
        boolean success = mockSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry);

        assertThat("Checking that SharedPreferenceEntry.save... returns true",
                success, is(true));

        // Read personal information from SharedPreferences
        SharedPreferenceEntry savedSharedPreferenceEntry =
                mockSharedPreferencesHelper.getPersonalInfo();

        // Make sure both written and retrieved personal information are equal.
        assertThat("Checking that SharedPreferenceEntry.username has been persisted and read correctly",
                sharedPreferenceEntry.getUsername(),
                is(equalTo(savedSharedPreferenceEntry.getUsername())));
        assertThat("Checking that SharedPreferenceEntry.name has been persisted and read "
                        + "correctly",
                sharedPreferenceEntry.getName(),
                is(equalTo(savedSharedPreferenceEntry.getName())));
        assertThat("Checking that SharedPreferenceEntry.lastName has been persisted and read "
                        + "correctly",
                sharedPreferenceEntry.getLastName(),
                is(equalTo(savedSharedPreferenceEntry.getLastName())));
        assertThat("Checking that SharedPreferenceEntry.email has been persisted and read "
                        + "correctly",
                sharedPreferenceEntry.getEmail(),
                is(equalTo(savedSharedPreferenceEntry.getEmail())));
        assertThat("Checking that SharedPreferenceEntry.phone has been persisted and read "
                        + "correctly",
                sharedPreferenceEntry.getPhone(),
                is(equalTo(savedSharedPreferenceEntry.getPhone())));
    }

    @Test
    public void sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        // Read personal information from a broken SharedPreferenceHelper
        boolean success =
                mockBrokenSharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry);
        assertThat("Makes sure writing to a broken SharedPreferenceHelper returns false", success,
                is(false));
    }

    /**
     * Creates a mocked SharedPreferences.
     */
    private SharedPreferenceHelper createMockSharedPreference() {
        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.
        when(mockSharedPreferences.getString(eq(SharedPreferenceHelper.KEY_USERNAME), anyString()))
                .thenReturn(sharedPreferenceEntry.getUsername());
        when(mockSharedPreferences.getString(eq(SharedPreferenceHelper.KEY_NAME), anyString()))
                .thenReturn(sharedPreferenceEntry.getName());
        when(mockSharedPreferences.getString(eq(SharedPreferenceHelper.KEY_LAST_NAME), anyString()))
                .thenReturn(sharedPreferenceEntry.getLastName());
        when(mockSharedPreferences.getString(eq(SharedPreferenceHelper.KEY_EMAIL), anyString()))
                .thenReturn(sharedPreferenceEntry.getEmail());
        when(mockSharedPreferences.getString(eq(SharedPreferenceHelper.KEY_PHONE), anyString()))
                .thenReturn(sharedPreferenceEntry.getPhone());

        // Mocking a successful commit.
        when(mockEditor.commit()).thenReturn(true);

        // Return the MockEditor when requesting it.
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        return new SharedPreferenceHelper(mockSharedPreferences);
    }

    /**
     * Creates a mocked SharedPreferences that fails when writing.
     */
    private SharedPreferenceHelper createBrokenMockSharedPreference() {
        // Mocking a commit that fails.
        when(mockBrokenEditor.commit()).thenReturn(false);

        // Return the broken MockEditor when requesting it.
        when(mockBrokenSharedPreferences.edit()).thenReturn(mockBrokenEditor);
        return new SharedPreferenceHelper(mockBrokenSharedPreferences);
    }



}
