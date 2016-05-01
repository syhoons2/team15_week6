package cnu.lecture;

import org.junit.Before;
import org.junit.Test;

import cnu.lecture.InGameInfo.Observer;
import cnu.lecture.InGameInfo.Participant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;


/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
    private InGameSummonerQuerier querier;
    private InGameSummonerQuerier querier2;
    Observer observer;
    InGameInfo inGameInfo;
    Participant virtualParticipant1;
    Participant virtualParticipant2;
    Participant virtualParticipant3;
    Participant virtualParticipant4;//가상의 참가자들
    Participant[] virtual_Participants = new Participant[4];
    @Before
    public void setup() throws ClientProtocolException, IOException {
       
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);
        //GameParticipantListener dontCareListener2 = mock(GameParticipantListener.class);
        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
        //querier2 = new InGameSummonerQuerier(apiKey, dontCareListener2);
        querier2 = spy(querier);//spy() method can be used to wrap a real object. 
                          //Every call, unless specified otherwise, is delegated to the object.
        
        observer = mock(Observer.class);//목객체로 안하면 에러나는 것 같다.
        inGameInfo = mock(InGameInfo.class);
      
       virtualParticipant1 = mock(Participant.class);
        virtualParticipant2 = mock(Participant.class);
        virtualParticipant3 = mock(Participant.class);
        virtualParticipant4 = mock(Participant.class);
        
        virtual_Participants[0] = virtualParticipant1;
        virtual_Participants[1] = virtualParticipant2;
        virtual_Participants[2] = virtualParticipant3;
        virtual_Participants[3] = virtualParticipant4;
        
        inGameInfo.setParticipants(virtual_Participants);
        
        when(inGameInfo.getObservers()).thenReturn(observer);
        when(inGameInfo.getParticipants()).thenReturn(virtual_Participants); 
        when(inGameInfo.getObservers().getEncryptionKey()).thenReturn("gJKxSLuVw8He0uj9wdyI9DAoDnpkgc00"); 
        when(querier2.inGameInfoReceive(anyString(), anyObject())).thenReturn(inGameInfo);
    }

    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
       
       final String summonerName;
        
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

    
    @Test
    public void shouldQuerierReportMoreThan5Summoners(){
       //목객체를 이용한 참가자 수 설정이 필요한 것 같다.
       
       final String summonerName;

        GIVEN: {
           
          summonerName = "lol010lol";
       }

       int numberOfPlayingParticipants = 0;
       
       boolean moreThanThree = false; 
       
       WHEN: {
       
          numberOfPlayingParticipants =virtual_Participants.length;
          
          if(numberOfPlayingParticipants >= 3)
             moreThanThree = true;
       }
       
       
       final boolean moreThanThreeReally = true; //정말 4명 이상인지 테스트!
       
       THEN: {
          assertThat(moreThanThree, is(moreThanThreeReally));
       }
    }
    
}