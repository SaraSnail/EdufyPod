package com.example.EdufyPod.services;

import com.example.EdufyPod.clients.CreatorClient;
import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.clients.ThumbClient;
import com.example.EdufyPod.clients.UserClient;
import com.example.EdufyPod.exceptions.*;
import com.example.EdufyPod.models.DTO.IncomingPodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.GenreDTO;
import com.example.EdufyPod.models.DTO.callDTOs.MediaByGenreDTO;
import com.example.EdufyPod.models.DTO.callDTOs.UserDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.models.enums.MediaType;
import com.example.EdufyPod.repositories.PodcastRepository;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//ED-343-SA
@ExtendWith(MockitoExtension.class)
class PodcastServiceImplUnitTest {
    @Mock
    private PodcastRepository podcastRepository;//ED-343-SA
    @Mock
    private CreatorClient creatorClient;//ED-343-SA
    @Mock
    private GenreClient genreClient;//ED-343-SA
    @Mock
    private ThumbClient thumbClient;//ED-343-SA
    @Mock
    private UserClient userClient;//ED-343-SA
    @Mock
    private PodcastSeasonRepository podcastSeasonRepository;//ED-343-SA

    @InjectMocks
    private PodcastServiceImpl podcastService;//ED-343-SA

    private final Authentication mockAuthentication = mock(Authentication.class);//ED-343-SA

    //ED-343-SA
    private final Map<Long, Long> userHistory = new HashMap<>();
    private final Podcast podcast = new Podcast();
    private final PodcastSeason season = new PodcastSeason();

    //ED-343-SA
    @BeforeEach
    void setUp() {

        season.setId(1L);
        season.setTitle("Season 1");
        season.setDescription("Season 1 details");
        season.setUrl("http://season.url");
        season.setReleaseDate(LocalDate.of(2025,12,30));
        season.setActive(true);

        userHistory.put(1L, 1L);
        podcast.setId(1L);
        podcast.setTitle("Podcast Title");
        podcast.setUrl("http://podcast.url");
        podcast.setDescription("Podcast Description");
        podcast.setReleaseDate(LocalDate.of(2025,12,30));
        podcast.setLength(Duration.ofSeconds(2300));
        podcast.setNrInSeason(1);
        podcast.setSeason(season);
        podcast.setTimesListened(0L);
        podcast.setActive(false);
        podcast.setUserHistory(userHistory);

        season.setPodcasts(List.of(podcast));
    }


    ///Get Podcast By Id
    //ED-343-SA
    @Test
    void getPodcastById_ShouldReturnPodcastDTO() {
        //arrange
        when(podcastRepository.findById(1L)).thenReturn(Optional.of(podcast));
        when(creatorClient.getCreatorsEpisode(1L)).thenReturn(List.of(new CreatorDTO(1L,"username")));
        when(genreClient.getGenreEpisode(1L)).thenReturn(List.of(new GenreDTO(1L,"genrename")));

        //Act
        PodcastDTO result = podcastService.getPodcastById(1L);

        //Assert
        verify(podcastRepository).findById(1L);
        assertNotNull(result);
        assertEquals("Podcast Title", result.getTitle());

    }

    //ED-343-SA
    @Test
    void getPodcastById_ShouldThrowIfPodcastDoesNotExist() {
        //Arrange
        when(podcastRepository.findById(0L)).thenReturn(Optional.empty());

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.getPodcastById(0L)
        );

        //Assert
        assertEquals("No Podcast with id [0] found", exception.getMessage());
    }


    ///Get Podcast By Title
    //ED-343-SA
    @Test
    void getPodcastByTitle_ShouldReturnListOfPodcastDTO() {
        //Arrange
        when(podcastRepository.findAllByTitleContainingIgnoreCaseAndIsActiveTrue("Podcast")).thenReturn(List.of(podcast));
        when(creatorClient.getCreatorsEpisode(1L)).thenReturn(List.of(new CreatorDTO(1L,"username")));
        when(genreClient.getGenreEpisode(1L)).thenReturn(List.of(new GenreDTO(1L,"genrename")));

        //Act
        List<PodcastDTO> result = podcastService.getPodcastByTitle("Podcast");

        //Assert
        verify(podcastRepository).findAllByTitleContainingIgnoreCaseAndIsActiveTrue("Podcast");
        assertNotNull(result);
        assertEquals("Podcast Title", result.getFirst().getTitle());
    }

    //ED-343-SA
    @Test
    void getPodcastByTitle_ShouldThrowIfPodcastWithTitleDoesNotExistOrIsActiveFalse() {
        //Arrange
        when(podcastRepository.findAllByTitleContainingIgnoreCaseAndIsActiveTrue("empty")).thenReturn(Collections.emptyList());

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.getPodcastByTitle("empty")
        );

        //Assert
        assertEquals("No Podcast with title containing [empty] found", exception.getMessage());
    }



    ///Get all podcasts
    //ED-343-SA
    @Test
    void getAllPodcasts_ShouldReturnListOfPodcastDTOForAdmin() {
        //Arrange
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_pod_admin");
        List<GrantedAuthority> authorities = List.of(authority);
        lenient().when(mockAuthentication.getAuthorities()).thenReturn((Collection) authorities);

        when(podcastRepository.findAll()).thenReturn(List.of(podcast));
        when(creatorClient.getCreatorsEpisode(1L)).thenReturn(List.of(new CreatorDTO(1L,"username")));
        when(genreClient.getGenreEpisode(1L)).thenReturn(List.of(new GenreDTO(1L,"genrename")));

        //Act
        List<PodcastDTO> result = podcastService.getAllPodcasts(mockAuthentication);

        //Assert
        verify(podcastRepository).findAll();
        assertNotNull(result);
        assertEquals("Podcast Title", result.getFirst().getTitle());

    }

    //ED-343-SA
    @Test
    void getAllPodcasts_ShouldReturnListOfPodcastDTOForRealmAdmin() {
        //Arrange
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_edufy_realm_admin");
        List<GrantedAuthority> authorities = List.of(authority);
        lenient().when(mockAuthentication.getAuthorities()).thenReturn((Collection) authorities);

        when(podcastRepository.findAll()).thenReturn(List.of(podcast));
        when(creatorClient.getCreatorsEpisode(1L)).thenReturn(List.of(new CreatorDTO(1L,"username")));
        when(genreClient.getGenreEpisode(1L)).thenReturn(List.of(new GenreDTO(1L,"genrename")));

        //Act
        List<PodcastDTO> result = podcastService.getAllPodcasts(mockAuthentication);

        //Assert
        verify(podcastRepository).findAll();
        assertNotNull(result);
        assertEquals("Podcast Title", result.getFirst().getTitle());

    }

    //ED-343-SA
    @Test
    void getAllPodcasts_ShouldThrowIfNoPodcastsFound_Admin(){
        //Arrange
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_pod_admin");
        List<GrantedAuthority> authorities = List.of(authority);
        lenient().when(mockAuthentication.getAuthorities()).thenReturn((Collection) authorities);

        when(podcastRepository.findAll()).thenReturn(List.of());

        //Act
        assertThrows(
                ContentNotFoundException.class,
                () -> podcastService.getAllPodcasts(mockAuthentication)
        );

        verify(podcastRepository).findAll();
    }

    //ED-343-SA
    @Test
    void getAllPodcasts_ShouldThrowIfNoPodcastsFound_RealmAdmin(){
        //Arrange
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_edufy_realm_admin");
        List<GrantedAuthority> authorities = List.of(authority);
        lenient().when(mockAuthentication.getAuthorities()).thenReturn((Collection) authorities);

        when(podcastRepository.findAll()).thenReturn(List.of());

        //Act
        assertThrows(
                ContentNotFoundException.class,
                () -> podcastService.getAllPodcasts(mockAuthentication)
        );

        verify(podcastRepository).findAll();
    }

    //ED-343-SA
    @Test
    void getAllPodcasts_ShouldReturnListOfPodcastDTOForUser() {
        //Arrange
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_pod_user");
        List<GrantedAuthority> authorities = List.of(authority);
        lenient().when(mockAuthentication.getAuthorities()).thenReturn((Collection) authorities);

        when(podcastRepository.findAllByIsActiveTrue()).thenReturn(List.of(podcast));
        when(creatorClient.getCreatorsEpisode(1L)).thenReturn(List.of(new CreatorDTO(1L,"username")));
        when(genreClient.getGenreEpisode(1L)).thenReturn(List.of(new GenreDTO(1L,"genrename")));

        //Act
        List<PodcastDTO> result = podcastService.getAllPodcasts(mockAuthentication);

        //Assert
        verify(podcastRepository).findAllByIsActiveTrue();
        assertNotNull(result);
        assertEquals("Podcast Title", result.getFirst().getTitle());
    }

    //ED-343-SA
    @Test
    void getAllPodcasts_ShouldThrowIfNoPodcastsFound_User(){
        //Arrange
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_pod_user");
        List<GrantedAuthority> authorities = List.of(authority);
        lenient().when(mockAuthentication.getAuthorities()).thenReturn((Collection) authorities);

        when(podcastRepository.findAllByIsActiveTrue()).thenReturn(List.of());

        //Act
        assertThrows(
                ContentNotFoundException.class,
                () -> podcastService.getAllPodcasts(mockAuthentication)
        );

        verify(podcastRepository).findAllByIsActiveTrue();
    }



    /// Create Podcast
    //ED-343-SA
    @Test
    void createPodcast_ShouldReturnNewPodcastDTOUrlHttp() {
        //Arrange
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.existsByUrl(incoming.getUrl())).thenReturn(false);

        when(podcastRepository.save(any(Podcast.class))).thenAnswer(inv -> {
            Podcast p = inv.getArgument(0);
            p.setId(2L);
            return p;
        });

        when(creatorClient.getCreatorsEpisode(2L)).thenReturn(List.of(creatorDTO));
        when(genreClient.getGenreEpisode(2L)).thenReturn(List.of(genreDTO));

        doNothing().when(creatorClient).createRecordOfMedia(anyLong(),any(),any());
        doNothing().when(genreClient).createRecordOfMedia(anyLong(),any(),any());
        doNothing().when(thumbClient).createRecordOfMedia(anyLong(),any(),anyString());

        //Act
        PodcastDTO result = podcastService.createPodcast(incoming);

        //Assert
        assertNotNull(result);
        assertEquals("New Podcast", result.getTitle());
        assertEquals(2L, result.getId());

    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldReturnNewPodcastDTOUrlHttps() {
        //Arrange
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "https://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.existsByUrl(incoming.getUrl())).thenReturn(false);

        when(podcastRepository.save(any(Podcast.class))).thenAnswer(inv -> {
            Podcast p = inv.getArgument(0);
            p.setId(2L);
            return p;
        });

        when(creatorClient.getCreatorsEpisode(2L)).thenReturn(List.of(creatorDTO));
        when(genreClient.getGenreEpisode(2L)).thenReturn(List.of(genreDTO));

        doNothing().when(creatorClient).createRecordOfMedia(anyLong(),any(),any());
        doNothing().when(genreClient).createRecordOfMedia(anyLong(),any(),any());
        doNothing().when(thumbClient).createRecordOfMedia(anyLong(),any(),anyString());

        //Act
        PodcastDTO result = podcastService.createPodcast(incoming);

        //Assert
        assertNotNull(result);
        assertEquals("New Podcast", result.getTitle());
        assertEquals(2L, result.getId());

    }

    //Validate fails
    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfCreatorNotFound() {
        //Arrange
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "https://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(null);

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.createPodcast(incoming));

        //Assert
        assertEquals("No Creator with id [1] found", exception.getMessage());

    }



    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfGenreNotFound() {
        //Arrange
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "https://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        when(genreClient.getGenreById(1L)).thenReturn(null);

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.createPodcast(incoming));

        //Assert
        assertEquals("No Genre with id [1] found", exception.getMessage());

    }

    //Null values
    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfTitleIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                null,
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field Title cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfUrlIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                null,
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field URL cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfDescriptionIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                null,
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field Description cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfCreatorIdsIsNull() {
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                null,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field CreatorIds cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfReleaseDateIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                null,
                genreIds,
                "0:34:20",
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field ReleaseDate cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfGenreIdsIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                null,
                "0:34:20",
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field GenreIds cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfLengthIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                null,
                1,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field Length cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfNrInSeasonIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                null,
                1L);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field NrInSeason cannot have the value [null]", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfSeasonIdIsNull() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                null);

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field SeasonId cannot have the value [null]", exception.getMessage());
    }


    //ED-343-SA
    @Test
    void createPodcast_ShouldThrowIfSeasonIdDoesNotExist() {
        //Arrange
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.empty());

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.createPodcast(incoming)
        );

        //Assert
        assertEquals("No PodcastSeason with id [1] found", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_WontMoveOtherPodcastNrInSeasonIfNotTheSameSpot() {
        //Arrange
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                2,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.save(any(Podcast.class))).thenAnswer(inv -> {
            Podcast p = inv.getArgument(0);
            p.setId(2L);
            return p;
        });

        when(creatorClient.getCreatorsEpisode(2L)).thenReturn(List.of(creatorDTO));
        when(genreClient.getGenreEpisode(2L)).thenReturn(List.of(genreDTO));

        doNothing().when(creatorClient).createRecordOfMedia(anyLong(),any(),any());
        doNothing().when(genreClient).createRecordOfMedia(anyLong(),any(),any());
        doNothing().when(thumbClient).createRecordOfMedia(anyLong(),any(),anyString());

        //Act
        PodcastDTO result = podcastService.createPodcast(incoming);

        //Assert
        assertNotNull(result);
        assertEquals("New Podcast", result.getTitle());
        assertEquals(2L, result.getId());
    }


    //Check values
    //ED-343-SA
    @Test
    void createPodcast_CheckValuesShouldThrowIfTitleIsEmpty() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field Title cannot have the value []", exception.getMessage());

    }

    //ED-343-SA
    @Test
    void createPodcast_CheckValuesShouldThrowIfTitleIsLessThan3Characters() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "O",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        ValidFieldsException exception = assertThrows(
                ValidFieldsException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field Title has the condition to be 'more than 3 characters', while the value given is [O]", exception.getMessage());

    }

    //ED-343-SA
    @Test
    void createPodcast_CheckValuesShouldThrowIfUrlIsEmpty() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field URL cannot have the value []", exception.getMessage());

    }

    //ED-343-SA
    @Test
    void createPodcast_CheckValuesShouldThrowIfUrlIsNotUnique() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.existsByUrl(incoming.getUrl())).thenReturn(true);

        UniqueConflictException exception = assertThrows(
                UniqueConflictException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("URL: [http://newpodcast.url] already exists, duplicates is not allowed", exception.getMessage());

    }

    //ED-343-SA
    @Test
    void createPodcast_CheckValuesShouldThrowIfUrlDoseNotContainHttpOrHttps() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "newpodcast.url",
                "new podcast description",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);

        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.existsByUrl(incoming.getUrl())).thenReturn(false);

        ValidFieldsException exception = assertThrows(
                ValidFieldsException.class,
                () -> podcastService.createPodcast(incoming)
        );

        assertEquals("The field URL has the condition to be 'needs to contain either http:// or https://', while the value given is [newpodcast.url]", exception.getMessage());

    }

    //ED-343-SA
    @Test
    void createPodcast_CheckValuesThrowsIdDescriptionIsEmpty() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);


        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.existsByUrl(incoming.getUrl())).thenReturn(false);


        NullValueException exception = assertThrows(
                NullValueException.class,
                () -> podcastService.createPodcast(incoming)
        );


        assertEquals("The field Description cannot have the value []", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void createPodcast_CheckValuesShouldThrowIfDescriptionIsLessThan10Characters() {
        List<Long> creatorsIds = List.of(1L);
        List<Long> genreIds = List.of(1L);
        IncomingPodcastDTO incoming = new IncomingPodcastDTO(
                "New Podcast",
                "http://newpodcast.url",
                "O",
                creatorsIds,
                LocalDate.of(2025,12,30),
                genreIds,
                "0:34:20",
                1,
                1L);


        CreatorDTO creatorDTO = new CreatorDTO(1L,"username");
        GenreDTO genreDTO = new GenreDTO(1L,"genrename");

        when(genreClient.getGenreById(1L)).thenReturn(genreDTO);
        when(creatorClient.getCreatorById(1L)).thenReturn(creatorDTO);

        when(podcastSeasonRepository.findById(1L)).thenReturn(Optional.of(season));
        when(podcastRepository.findAllBySeasonOrdered(1L)).thenReturn(List.of(podcast));

        when(podcastRepository.existsByUrl(incoming.getUrl())).thenReturn(false);


        ValidFieldsException exception = assertThrows(
                ValidFieldsException.class,
                () -> podcastService.createPodcast(incoming)
        );


        assertEquals("The field Description has the condition to be 'more than 10 characters', while the value given is [O]", exception.getMessage());
    }


    ///User history
    //ED-343-SA
    @Test
    void getUserHistory_ShouldReturnListPodcastDTOs() {
        //Arrange
        UserDTO userDTO = new UserDTO(1L);
        when(userClient.getUserById(1L)).thenReturn(userDTO);
        when(podcastRepository.findPodcastIdsPlayedByUser(1L)).thenReturn(List.of(1L));
        when(podcastRepository.findById(1L)).thenReturn(Optional.of(podcast));

        //Act
        List<PodcastDTO> result = podcastService.getUserHistory(1L);

        //Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getId());
    }

    //ED-343-SA
    @Test
    void getUserHistory_ShouldThrowIdUserNotPlayedAnyPodcasts() {
        //Arrange
        UserDTO userDTO = new UserDTO(1L);
        when(userClient.getUserById(1L)).thenReturn(userDTO);
        when(podcastRepository.findPodcastIdsPlayedByUser(1L)).thenReturn(List.of());

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.getUserHistory(1L)
        );

        //Assert
        assertEquals("No Podcast ids with userId [1] found", exception.getMessage());
    }

    //ED-343-SA
    @Test
    void getUserHistory_ShouldThrowIfPodcastNotFound() {
        //Arrange
        UserDTO userDTO = new UserDTO(1L);
        when(userClient.getUserById(1L)).thenReturn(userDTO);
        when(podcastRepository.findPodcastIdsPlayedByUser(1L)).thenReturn(List.of(1L));
        when(podcastRepository.findById(1L)).thenReturn(Optional.empty());

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.getUserHistory(1L)
        );

        //Assert
        assertEquals("No Podcast with id [1] found", exception.getMessage());
    }



    ///Get podcast by genre
    //ED-343-SA
    @Test
    void getPodcastsByGenre_ShouldReturnListPodcastDTOs() {
        //Arrange
        MediaByGenreDTO mediaByGenreDTO = new MediaByGenreDTO("genrename",List.of(1L));
        when(genreClient.getMediaByGenreId(1L, MediaType.PODCAST_EPISODE)).thenReturn(mediaByGenreDTO);
        when(podcastRepository.findById(1L)).thenReturn(Optional.of(podcast));

        when(creatorClient.getCreatorsEpisode(1L)).thenReturn(List.of(new CreatorDTO(1L,"username")));
        when(genreClient.getGenreEpisode(1L)).thenReturn(List.of(new GenreDTO(1L,"genrename")));

        //Act
        List<PodcastDTO> result = podcastService.getPodcastsByGenre(1L);

        //Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Podcast Title", result.getFirst().getTitle());
    }

    //ED-343-SA
    @Test
    void getPodcastsByGenre_ShouldThrowIfPodcastNotFound() {
        //Arrange
        MediaByGenreDTO mediaByGenreDTO = new MediaByGenreDTO("genrename",List.of(1L));
        when(genreClient.getMediaByGenreId(1L, MediaType.PODCAST_EPISODE)).thenReturn(mediaByGenreDTO);
        when(podcastRepository.findById(1L)).thenReturn(Optional.empty());

        //Act
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> podcastService.getPodcastsByGenre(1L)
        );

        //Assert
        assertEquals("No Podcast with id [1] found", exception.getMessage());
    }


    ///Play Podcast
    //ED-343-SA
    @Test
    void playPodcast() {
    }
}