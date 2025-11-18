INSERT INTO podcast_season(podcast_season_id, podcast_season_title, podcast_season_description, podcast_season_url, podcast_season_release_date, podcast_season_is_active) VALUES
(1, 'Voices of Tomorrow', 'A deep dive into the tech shaping our future.', 'https://pod.example.com/season/1', '2023-01-05', true),
(2, 'Echoes of History', 'Exploring untold stories and forgotten events.', 'https://pod.example.com/season/2', '2023-02-10',  true),
(3, 'Mind Over Matter', 'Psychology, philosophy, and the art of thinking.', 'https://pod.example.com/season/3', '2023-03-15', true),
(4, 'The Cosmic Frequency', 'Space, time, and everything in between.', 'https://pod.example.com/season/4', '2023-04-20',  true),
(5, 'Urban Legends Uncovered', 'Myths and mysteries behind famous stories.', 'https://pod.example.com/season/5', '2023-05-25',  true),
(6, 'Creative Minds', 'Conversations with artists, writers, and innovators.', 'https://pod.example.com/season/6', '2023-06-30', true),
(7, 'The Sound of Science', 'Breaking down complex discoveries in simple terms.', 'https://pod.example.com/season/7', '2023-08-04',  true),
(8, 'Future Frontiers', 'Exploring the edge of innovation and exploration.', 'https://pod.example.com/season/8', '2023-09-08', true),
(9, 'Coffee and Conversations', 'Casual talks with extraordinary people.', 'https://pod.example.com/season/9', '2023-10-13', true),
(10, 'The Hidden Signal', 'Investigating mysteries of sound, frequency, and rhythm.', 'https://pod.example.com/season/10', '2023-11-17', true);

-- changed time to duration, seconds //ED-232-SA
INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(1, 'The Dawn of AI', 'https://pod.example.com/ep/1', 'An exploration into artificial intelligence and its impact on daily life.', '2024-01-06', 2520, 1, 1520, true,1),
(2, 'Voices from Mars', 'https://pod.example.com/ep/2', 'Scientists discuss communication with future Mars colonies.', '2024-01-13', 2302, 2, 1310, true,1),
(3, 'Digital Utopia', 'https://pod.example.com/ep/3', 'What would a world run by AI truly look like?', '2024-01-20', 2493, 3, 1665, true,1),
(4, 'Machine Morality', 'https://pod.example.com/ep/4', 'How do we teach robots ethics?', '2024-01-27', 2358, 4, 1422, true,1),
(5, 'The End of Privacy', 'https://pod.example.com/ep/5', 'Experts debate the future of privacy in a data-driven world.', '2024-02-03', 2639, 5, 1980, true,1),
(6, 'The Virtual Brain', 'https://pod.example.com/ep/6', 'Neuroscientists on the merging of man and machine.', '2024-02-10', 2424, 6, 1205, true,1),
(7, 'Conscious Code', 'https://pod.example.com/ep/7', 'Could consciousness emerge from algorithms?', '2024-02-17', 2671, 7, 1783, true,1),
(8, 'The Singularity Nears', 'https://pod.example.com/ep/8', 'When technology surpasses human intelligence.', '2024-02-24', 2823, 8, 1650, true,1),
(9, 'Quantum Thought', 'https://pod.example.com/ep/9', 'Quantum computing and human cognition.', '2024-03-02', 2204, 9, 1429, true,1),
(10, 'The Human Algorithm', 'https://pod.example.com/ep/10', 'What humans can learn from AI design.', '2024-03-09', 2390, 10, 1556, true,1);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(11, 'Echoes of Rome', 'https://pod.example.com/ep/11', 'Ancient Rome through sound and storytelling.', '2024-03-16', 2487, 1, 1320, true,2),
(12, 'Lost Cities Found', 'https://pod.example.com/ep/12', 'Archaeologists reveal new digital reconstructions.', '2024-03-23', 2622, 2, 1489, true,2),
(13, 'The Fall of Empires', 'https://pod.example.com/ep/13', 'How great civilizations collapse.', '2024-03-30', 2316, 3, 1701, true,2),
(14, 'The Viking Echo', 'https://pod.example.com/ep/14', 'Uncovering real Norse myths and truths.', '2024-04-06', 2788, 4, 1240, true,2),
(15, 'Mystery of the Mayans', 'https://pod.example.com/ep/15', 'What happened to the Maya civilization?', '2024-04-13', 2380, 5, 1560, true,2),
(16, 'Greek Machines', 'https://pod.example.com/ep/16', 'How advanced were ancient technologies?', '2024-04-20', 2530, 6, 1732, true,2),
(17, 'The Library of Alexandria', 'https://pod.example.com/ep/17', 'A look into lost knowledge.', '2024-04-27', 2277, 7, 1891, true,2),
(18, 'Forgotten Wars', 'https://pod.example.com/ep/18', 'Conflicts that changed history but few remember.', '2024-05-04', 2405, 8, 1675, true,2),
(19, 'Renaissance Sparks', 'https://pod.example.com/ep/19', 'The rebirth of art and thought.', '2024-05-11', 2661, 9, 1942, true,2),
(20, 'Empire of Dust', 'https://pod.example.com/ep/20', 'The cyclical nature of power.', '2024-05-18', 2392, 10, 1536, true,2);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(21, 'The Conscious Mind', 'https://pod.example.com/ep/21', 'Understanding human thought through modern psychology.', '2024-05-25', 2469, 1, 1610, true,3),
(22, 'Dream Architecture', 'https://pod.example.com/ep/22', 'Exploring the structure of dreams and memory.', '2024-06-01', 2562, 2, 1577, true,3),
(23, 'Cognitive Biases', 'https://pod.example.com/ep/23', 'How our minds trick us every day.', '2024-06-08', 2351, 3, 1719, true,3),
(24, 'Philosophy of Self', 'https://pod.example.com/ep/24', 'What makes you you?', '2024-06-15', 2453, 4, 1498, true,3),
(25, 'Habits and Change', 'https://pod.example.com/ep/25', 'Building and breaking habits effectively.', '2024-06-22', 2258, 5, 1624, true,3),
(26, 'Attention Economy', 'https://pod.example.com/ep/26', 'The cost of constant distraction.', '2024-06-29', 2646, 6, 1583, true,3),
(27, 'Language and Thought', 'https://pod.example.com/ep/27', 'Does language shape the mind?', '2024-07-06', 2491, 7, 1722, true,3),
(28, 'Moral Psychology', 'https://pod.example.com/ep/28', 'Why do we judge right and wrong?', '2024-07-13', 2594, 8, 1689, true,3),
(29, 'Memory Palaces', 'https://pod.example.com/ep/29', 'Techniques to remember anything.', '2024-07-20', 2325, 9, 1754, true,3),
(30, 'Meaning and Purpose', 'https://pod.example.com/ep/30', 'Finding significance in everyday life.', '2024-07-27', 2427, 10, 1633, true,3);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(31, 'Starlight and Gravity', 'https://pod.example.com/ep/31', 'How stars bend space and time.', '2024-08-03', 2557, 1, 1820, true,4),
(32, 'Dark Matter Diaries', 'https://pod.example.com/ep/32', 'Chasing the invisible mass of the cosmos.', '2024-08-10', 2369, 2, 1541, true,4),
(33, 'Exoplanet Atlas', 'https://pod.example.com/ep/33', 'Worlds beyond our own.', '2024-08-17', 2695, 3, 1904, true,4),
(34, 'Signals from Space', 'https://pod.example.com/ep/34', 'Mysterious radio bursts and what they mean.', '2024-08-24', 2241, 4, 1510, true,4),
(35, 'Life in the Ice', 'https://pod.example.com/ep/35', 'Astrobiology under extreme conditions.', '2024-08-31', 2473, 5, 1698, true,4),
(36, 'Telescope Titans', 'https://pod.example.com/ep/36', 'The machines that let us see the beginning.', '2024-09-07', 2583, 6, 1766, true,4),
(37, 'Cosmic Inflation', 'https://pod.example.com/ep/37', 'The universe in its first moments.', '2024-09-14', 2408, 7, 1677, true,4),
(38, 'Rings and Things', 'https://pod.example.com/ep/38', 'The physics of planetary rings.', '2024-09-21', 2387, 8, 1509, true,4),
(39, 'Galactic Cartography', 'https://pod.example.com/ep/39', 'Mapping the Milky Way.', '2024-09-28', 2712, 9, 1886, true,4),
(40, 'Edge of the Observable', 'https://pod.example.com/ep/40', 'What lies beyond what we can see?', '2024-10-05', 2545, 10, 1711, true,4);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(41, 'City Myths', 'https://pod.example.com/ep/41', 'Sorting truth from fiction in urban legends.', '2024-10-12', 2299, 1, 1402, true,5),
(42, 'Haunted Highways', 'https://pod.example.com/ep/42', 'Ghost stories from the open road.', '2024-10-19', 2504, 2, 1525, true,5),
(43, 'Mysterious Numbers', 'https://pod.example.com/ep/43', 'Creepy coincidences and cursed codes.', '2024-10-26', 2276, 3, 1479, true,5),
(44, 'Whispers in the Woods', 'https://pod.example.com/ep/44', 'Forest folklore and fear.', '2024-11-02', 2616, 4, 1652, true,5),
(45, 'Midnight Broadcast', 'https://pod.example.com/ep/45', 'The pirate radio that wouldn’t die.', '2024-11-09', 2341, 5, 1601, true,5),
(46, 'The Vanishing Hotel', 'https://pod.example.com/ep/46', 'A building that appears and disappears.', '2024-11-16', 2532, 6, 1718, true,5),
(47, 'The Mirror Game', 'https://pod.example.com/ep/47', 'Superstition or something more?', '2024-11-23', 2434, 7, 1493, true,5),
(48, 'Numbers on the Wall', 'https://pod.example.com/ep/48', 'Signals or graffiti?', '2024-11-30', 2658, 8, 1742, true,5),
(49, 'The Last Tape', 'https://pod.example.com/ep/49', 'A recording that changes every listen.', '2024-12-07', 2482, 9, 1687, true,5),
(50, 'After Midnight', 'https://pod.example.com/ep/50', 'Why things feel different at 3 a.m.', '2024-12-14', 2378, 10, 1628, true,5);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(51, 'The Art Habit', 'https://pod.example.com/ep/51', 'Daily creativity without burnout.', '2024-12-21', 2441, 1, 1514, true,6),
(52, 'Notes to a Novelist', 'https://pod.example.com/ep/52', 'First drafts, last doubts.', '2024-12-28', 2527, 2, 1555, true,6),
(53, 'Studio Rituals', 'https://pod.example.com/ep/53', 'How artists set the stage for work.', '2025-01-04', 2272, 3, 1466, true,6),
(54, 'Collaboration Myths', 'https://pod.example.com/ep/54', 'Working together without losing yourself.', '2025-01-11', 2667, 4, 1729, true,6),
(55, 'Finding a Voice', 'https://pod.example.com/ep/55', 'Authenticity in an algorithmic world.', '2025-01-18', 2373, 5, 1691, true,6),
(56, 'Creative Blocks', 'https://pod.example.com/ep/56', 'When inspiration stalls, systems help.', '2025-01-25', 2509, 6, 1588, true,6),
(57, 'The Feedback Loop', 'https://pod.example.com/ep/57', 'Critique that actually helps.', '2025-02-01', 2581, 7, 1761, true,6),
(58, 'Publishing Paths', 'https://pod.example.com/ep/58', 'Indie vs. traditional routes.', '2025-02-08', 2429, 8, 1622, true,6),
(59, 'Stagecraft', 'https://pod.example.com/ep/59', 'Performing without panic.', '2025-02-15', 2334, 9, 1499, true,6),
(60, 'The Aftershow', 'https://pod.example.com/ep/60', 'What happens when the curtain falls.', '2025-02-22', 2553, 10, 1716, true,6);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(61, 'Everyday Physics', 'https://pod.example.com/ep/61', 'Science hidden in plain sight.', '2025-03-01', 2368, 1, 1542, true,7),
(62, 'Kitchen Chemistry', 'https://pod.example.com/ep/62', 'Reactions you can taste.', '2025-03-08', 2516, 2, 1607, true,7),
(63, 'Biology of Belief', 'https://pod.example.com/ep/63', 'Placebos and perception.', '2025-03-15', 2624, 3, 1738, true,7),
(64, 'Weather Machines', 'https://pod.example.com/ep/64', 'Can we control the climate?', '2025-03-22', 2402, 4, 1659, true,7),
(65, 'Numbers that Lie', 'https://pod.example.com/ep/65', 'Statistics and misdirection.', '2025-03-29', 2266, 5, 1596, true,7),
(66, 'Time Travel Rules', 'https://pod.example.com/ep/66', 'Paradoxes and possibilities.', '2025-04-05', 2538, 6, 1774, true,7),
(67, 'Microscope Cities', 'https://pod.example.com/ep/67', 'The ecosystems on your skin.', '2025-04-12', 2463, 7, 1681, true,7),
(68, 'Sound as Medicine', 'https://pod.example.com/ep/68', 'Therapies built on vibration.', '2025-04-19', 2398, 8, 1507, true,7),
(69, 'The Fractal Edge', 'https://pod.example.com/ep/69', 'Patterns that repeat forever.', '2025-04-26', 2700, 9, 1869, true,7),
(70, 'The Science of Sleep', 'https://pod.example.com/ep/70', 'Why rest is revolutionary.', '2025-05-03', 2532, 10, 1725, true,7);

INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(71, 'Terraforming Tomorrow', 'https://pod.example.com/ep/71', 'From deserts to forests—can tech help?', '2025-05-10', 2381, 1, 1602, true,8),
(72, 'Ocean Farms', 'https://pod.example.com/ep/72', 'Feeding billions from the sea.', '2025-05-17', 2492, 2, 1619, true,8),
(73, 'Electric Skies', 'https://pod.example.com/ep/73', 'Air taxis and silent planes.', '2025-05-24', 2602, 3, 1756, true,8),
(74, 'Quantum Internet', 'https://pod.example.com/ep/74', 'Unhackable networks?', '2025-05-31', 2411, 4, 1668, true,8),
(75, 'Food Without Farms', 'https://pod.example.com/ep/75', 'Cultured meat in your kitchen.', '2025-06-07', 2313, 5, 1586, true,8),
(76, 'Sunlight in a Bottle', 'https://pod.example.com/ep/76', 'New bets on fusion power.', '2025-06-14', 2684, 6, 1811, true,8),
(77, 'Cities that Breathe', 'https://pod.example.com/ep/77', 'Architecture for clean air.', '2025-06-21', 2426, 7, 1708, true,8),
(78, 'Edge Computing Everywhere', 'https://pod.example.com/ep/78', 'When the cloud comes to you.', '2025-06-28', 2526, 8, 1721, true,8),
(79, 'Mars Logistics', 'https://pod.example.com/ep/79', 'Supply chains beyond Earth.', '2025-07-05', 2347, 9, 1637, true,8),
(80, 'Frontiers of Health', 'https://pod.example.com/ep/80', 'Personalized medicine for all.', '2025-07-12', 2507, 10, 1748, true,8);


INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(81, 'Morning Rituals', 'https://pod.example.com/ep/81', 'Conversations over coffee with makers.', '2025-07-19', 2279, 1, 1509, true,9),
(82, 'Stories over Steam', 'https://pod.example.com/ep/82', 'Creativity brewed fresh.', '2025-07-26', 2373, 2, 1521, true,9),
(83, 'Table for Two', 'https://pod.example.com/ep/83', 'Intimate talks and big ideas.', '2025-08-02', 2565, 3, 1684, true,9),
(84, 'Notebook Margins', 'https://pod.example.com/ep/84', 'Ideas that didn’t fit anywhere else.', '2025-08-09', 2292, 4, 1576, true,9),
(85, 'Serendipity', 'https://pod.example.com/ep/85', 'Chance encounters that changed lives.', '2025-08-16', 2481, 5, 1661, true,9),
(86, 'Quiet Ambition', 'https://pod.example.com/ep/86', 'Doing meaningful work without the noise.', '2025-08-23', 2402, 6, 1608, true,9),
(87, 'Roadside Wisdom', 'https://pod.example.com/ep/87', 'Lessons learned on long drives.', '2025-08-30', 2619, 7, 1766, true,9),
(88, 'Late Night Pages', 'https://pod.example.com/ep/88', 'Journals, drafts, and honesty.', '2025-09-06', 2368, 8, 1640, true,9),
(89, 'Soundtrack Sundays', 'https://pod.example.com/ep/89', 'Music that made the moment.', '2025-09-13', 2539, 9, 1712, true,9),
(90, 'Coffee Break Live', 'https://pod.example.com/ep/90', 'Audience questions and open lines.', '2025-09-20', 2444, 10, 1695, true,9);


INSERT INTO podcast_episode(podcast_episode_id,podcast_episode_title,podcast_episode_url,podcast_episode_description, podcast_episode_release_date, podcast_episode_length, podcast_episode_nr_in_season, podcast_episode_times_listened, podcast_episode_is_active, podcast_episode_podcast_season) VALUES
(91, 'Signal in the Noise', 'https://pod.example.com/ep/91', 'Finding patterns where none should be.', '2025-09-27', 2518, 1, 1735, true,10),
(92, 'Whale Song', 'https://pod.example.com/ep/92', 'The physics and poetry of sound.', '2025-10-04', 2352, 2, 1567, true,10),
(93, 'Echo Chamber', 'https://pod.example.com/ep/93', 'Why we hear only what we want.', '2025-10-11', 2642, 3, 1827, true,10),
(94, 'Underground Frequencies', 'https://pod.example.com/ep/94', 'Signals that travel through stone.', '2025-10-18', 2436, 4, 1683, true,10),
(95, 'Sonic Boom', 'https://pod.example.com/ep/95', 'Breaking the sound barrier and beyond.', '2025-10-25', 2568, 5, 1759, true,10),
(96, 'Quiet Zones', 'https://pod.example.com/ep/96', 'Places where radio is forbidden.', '2025-11-01', 2320, 6, 1594, true,10),
(97, 'Voice Prints', 'https://pod.example.com/ep/97', 'Identification by sound alone.', '2025-11-08', 2466, 7, 1703, true,10),
(98, 'Rhythm of the Earth', 'https://pod.example.com/ep/98', 'Seismic songs and planet pulses.', '2025-11-15', 2598, 8, 1779, true,10),
(99, 'Numbers You Can Hear', 'https://pod.example.com/ep/99', 'Turning data into melody.', '2025-11-22', 2371, 9, 1627, true,10),
(100, 'The Hidden Signal', 'https://pod.example.com/ep/100', 'Tuning into what we usually miss.', '2025-11-29', 2547, 10, 1741, true,10);