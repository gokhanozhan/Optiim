Feature: N11 Favorite Actions 

Scenario: Login 
	Given import all elements 
	Given open browser with "Chrome" 
	Given navigate page "home_url" 
	Given find element "UserloginButton" 
	Given click element 
	Given find element "email" 
	Given write text element "gkhnozhn@hotmail.com" 
	Given find element "password" 
	Given write text element "Gokce22@" 
	Given find element "loginButton" 
	Given click element 
	Given find element "searchBar" 
	Given write text element "Samsung" 
	Given find element "searchButton" 
	Given click element 
	Given assert pageContains "Samsung için" 
	When find element "secondPage" 
	Then click element 
	When find element "favoriteAddFor3thProduct" 
	Then click element 
	When find element "myFavorites" 
	Then click element 
	Given assert pageContains "Favorilerim (1)" 
	When find element "favoritesDetail" 
	Then click element 
	When find element "deleteFavorite" 
	Then click element 
	Given assert pageContains "İzlediğiniz bir ürün bulunmamaktadır." 
	Then browser close
