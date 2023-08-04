# News Feed App

**News Feed App** is an Android application that allows users to browse and read the latest global news articles. It uses the Guardian API to fetch news data. Users can also add their local news to the app, which will be stored and displayed for other users in the same location.

## Features

- **Global News**: Fetches the latest global news articles from the Guardian API.
- **Local News**: Users can add local news and view news added by other users in the same location.
- **Location-based Filter**: Users can toggle a distance filter to show news added within a certain distance from their current location.
- **Upvote and Downvote**: Users can upvote or downvote local news articles added by other users.
- **Tab Layout**: The app has a tab layout that allows users to switch between Global and Local news views.

## Components

The app consists of the following major components:

1. **GlobalFragment**: This fragment displays the latest global news articles fetched from the Guardian API. It uses an `AsyncTask` to fetch the data and displays it in a `ListView` using the `NewsFeedAdapter`.

2. **NewsFeed**: A data model class that represents a news article with its title, section name, date, URL, and author name.

3. **JSONParser**: A utility class responsible for parsing the JSON data received from the Guardian API and converting it into a list of `NewsFeed` objects.

4. **LocalFragment**: This fragment allows users to add and view local news articles. Users can upvote or downvote news articles added by other users within a certain distance from their location. It uses a Firebase Realtime Database to store and retrieve local news data.

5. **NewsFeedLocal**: A data model class representing a local news article with its content.

6. **NewsFeedLocalAdapter**: An `ArrayAdapter` to display the list of local news articles added by users.

7. **GPSTracker**: A utility class to track the user's GPS location.

8. **MainActivity**: The main activity of the app that contains the `ViewPager` and `TabLayout` for switching between Global and Local news fragments.

9. **SimpleFragmentPagerAdapter**: A `FragmentPagerAdapter` that handles the switching between `GlobalFragment` and `LocalFragment`.

## Permissions

The app requires the following permissions:

- `INTERNET`: To fetch data from the Guardian API.
- `ACCESS_FINE_LOCATION`: To get the user's current GPS location for adding and filtering local news articles.

## How to Use

1. Launch the app, and you will see the Global news articles in the first tab.
2. Switch to the second tab to view and add local news articles.
3. Tap the "+" button to add a local news article. It will be stored in the Firebase Realtime Database.
4. Use the SeekBar to set the distance filter for viewing local news articles added by others.
5. Tap the upvote and downvote buttons to show your preference for other users' local news articles.

## Note

The app uses the Guardian API with a specific API key and filters the news based on a particular tag and date. If the app does not show any news articles, it could be due to the API key expiration or the absence of articles matching the filter criteria.

Please ensure you have a stable internet connection and have granted location permission to the app for proper functioning.

Happy reading!
