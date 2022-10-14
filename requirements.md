# Vision

The vision of this project is to provide a way for dog owners to conveniently connect with other dog owners for dog playdates. Dogs need socialization with other dogs, and this app provides an easy way to find opportunities for that. Well-socialized dogs are happier, healthier, and better behaved.

#Scope

## What it does/MVP

The app will allow users to set up profiles for their dog(s) (including profile pictures), create playdate events at nearby parks or a custom location, and find and RSVP to other users' events. Users can also "friend" each other if they want to get together again. The app will use an API to suggest nearby dog (or dog-friendly) parks for playdates.

## What it doesn't do

- No payment or money transfer features
- Will not track user's pinpoint location; will not provide user location to other users

## Stretch

- Users can communicate with each other using in-app messaging
- Integrate with Google calendar
- Admin roles with moderation capability

# Functional Requirements

Users can:
- create and edit their own profiles
- create, edit, and delete profiles for their dog(s)
- create events
- see nearby events that have been created
- RSVP to events
- view other users' profiles and connect with other users

## Data Flow

1. User opens the app and signs up or logs in
2. Sign up: goes to verification, then to login activities
3. After logging in, main activity will show a list of nearby playdates and any playdates the user has RSVP'd to
4. Tapping on any playdate goes to the playdate detail activity, where user can RSVP. Tapping RSVP brings up a confirmation activity.
5. From the main activity, the user can also access their profile, where they can edit their own profile, add a photo, and add dog profiles to their profile.
6. The playdate detail activity will have links to the organizer and all users who RSVP'd
7. When viewing other users' profiles, user can add that user as a friend

# Non-Functional Requirements

- Security: the app would work without requiring login, but for safety and accountability, we will require some form of logging in
- Testing: the app will pass a number of Espresso tests that we design to ensure it works how we want it to work
