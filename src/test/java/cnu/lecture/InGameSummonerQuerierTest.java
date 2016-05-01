package cnu.lecture;

import org.junit.Before;
import org.junit.Test;

import cnu.lecture.InGameInfo.Observer;
import cnu.lecture.InGameInfo.Participant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by tchi on 2016. 4. 25..
 */

/*
public class InGameSummonerQuerierTest {
    private InGameSummonerQuerier querier;

    @Before
    public void setup() {
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);

        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
    }

    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
        final String summonerName;

        GIVEN: {
            summonerName = "akane24";
        }

        final String actualGameKey;
        
        WHEN: {
            actualGameKey = querier.queryGameKey(summonerName);
            
        }

        final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
        THEN: {
            assertThat(actualGameKey, is(expectedGameKey));
        }
    }
}
*/


/*
public class InGameSummonerQuerierTest {
    private InGameSummonerQuerier querier;
    private InGameSummonerQuerier testQuerier;

 

    @Before
    public void setup() {
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);
        
        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
        testQuerier = new InGameSummonerQuerier(apiKey, dontCareListener);
    }
    
    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
        final String summonerName;
        
        Participant virtualParticipant1 = mock(Participant.class);
        //virtualParticipant1.setSummonerName("vp1");
        //Participant virtualParticipant2 = mock(Participant.class);

        //Participant virtualParticipant3 = mock(Participant.class);

        //Participant virtualParticipant4 = mock(Participant.class);

        //Participant[] virtual_Participants = {virtualParticipant1, virtualParticipant2, virtualParticipant3, virtualParticipant4,};
        Participant[] virtual_Participants = new Participant[1];
        virtual_Participants[0] = virtualParticipant1;
        Observer observer = mock(Observer.class);
        InGameInfo inGameInfo = mock(InGameInfo.class);
        inGameInfo.setParticipants(virtual_Participants);
        when(inGameInfo.getObservers()).thenReturn(observer);
        when(inGameInfo.getParticipants()).thenReturn(virtual_Participants); 
        when(inGameInfo.getObservers().getEncryptionKey()).thenReturn("gJKxSLuVw8He0uj9wdyI9DAoDnpkgc00"); 
        
        when(testQuerier.inGameInfoReceive(anyString(),anyObject())).thenReturn(inGameInfo);
        
        GIVEN: {
            summonerName = "akane24";
        }

        final String actualGameKey;
        
        WHEN: {
            actualGameKey = querier.queryGameKey(summonerName);
            
        }

        final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
        THEN: {
            assertThat(actualGameKey, is(expectedGameKey));
        }
    }
}
*/


public class InGameSummonerQuerierTest {
    private InGameSummonerQuerier querier;
    private InGameSummonerQuerier querier2;

    

    @Before
    public void setup() {
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);
        //GameParticipantListener dontCareListener2 = mock(GameParticipantListener.class);
        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
        querier2 = spy(querier);
        //querier2 = new InGameSummonerQuerier(apiKey, dontCareListener2);
    }

    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
        final String summonerName;
        
        Participant virtualParticipant1 = mock(Participant.class);
       
       // Participant virtualParticipant2 = mock(Participant.class);

        //Participant virtualParticipant3 = mock(Participant.class);

        //Participant virtualParticipant4 = mock(Participant.class);

        //Participant[] virtual_Participants = {virtualParticipant1, virtualParticipant2, virtualParticipant3, virtualParticipant4,};
        Participant[] virtual_Participants = new Participant[1];
        virtual_Participants[0] = virtualParticipant1;
        Observer observer = mock(Observer.class);
        InGameInfo inGameInfo = mock(InGameInfo.class);
        inGameInfo.setParticipants(virtual_Participants);
        
        when(inGameInfo.getObservers()).thenReturn(observer);
        when(inGameInfo.getParticipants()).thenReturn(virtual_Participants); 
        when(inGameInfo.getObservers().getEncryptionKey()).thenReturn("gJKxSLuVw8He0uj9wdyI9DAoDnpkgc00"); 
        
        when(querier2.inGameInfoReceive(anyString(), anyObject())).thenReturn(inGameInfo);
       
   
        GIVEN: {
            summonerName = "lol010lol";
            
        }

        final String actualGameKey;
        WHEN: {
           
            actualGameKey = querier2.queryGameKey(summonerName);
            
        }

        final String expectedGameKey = "gJKxSLuVw8He0uj9wdyI9DAoDnpkgc00";
        THEN: {
            assertThat(actualGameKey, is(expectedGameKey));
        }
        
    }
}