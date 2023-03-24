### Views lecture notes

Explain Thymeleaf
    can use plain NORMAL HTML files!
    replaces JSP EL and JSTL

- Do `pom.xml` dependency

#### Show how to return HTML instead of text from the controller 

- Start with the DogController
- Remove `@ResponseBody`
- return the base file name (NO EXTENSION)
- put HTML file in `resources/template`
  - highlight the Thymeleaf include at top of HTML file
- show results
  - show what happens if you forget to remove `@ResponseBody` 

#### Passing data to the view

- Yet another Model class :(
- demo example from lesson
- THEN work w dogs
- add an int and String attributes to the model
  - now make a query parameter for `/dogs?search=Bob`
    - I think the query parameter will be a `@RequestParam` parameter
    - view could be `dogs.html`
  - and make a `/dogs/99` path variable
    - will need to make a simple dog detail page `dogdetail.html`
- how to access them in the view
  - explain `th:text`
  - explain and show th:text placeholder (can run the HTML directly from IntelliJ)

#### Thymeleaf forms

- do the cohort join form
  - CHANGE `cohort-ip` to `cohort-id`
- now add a controller to GET the form
- demo GET /join
- now add the POST to the controller
- SHOW the `@RequestParam` and how to get the form element `cohort`
- feed the `cohort` to the model using `setAttribute`
- demo it

#### Thymeleaf loops and conditions

- add a validation check on the cohort name for `th:if="${errMsg}"`
  - use `model.setAttribute` to communicate an errMsg to thymeleaf
- make a bunch of Dogs and send those to an `index.html` page 
  - use `th:each="dog : ${dogs}"` to show all the dogs
  - use `th:text` for displaying each dog's info (prolly just id and name)

#### Templating

- show and explain head and navbar examples
- both use `replace` instead of `insert`
  - using insert instead of replace just depends on whether or not you want to keep what is already in the destination element
  - e.g., there is page-specific stuff in the head that you want to keep 