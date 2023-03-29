have a new cat form ready to go

now we get at those form fields via requestparams

point out that the field names correspond to the cat model property names
    instead of using separate field variables as requestparams, we can just pass the object itself
    this is form model binding
        binding one of our model objects (e.g., Cat) to a form using thymeleaf
            then the form is able to easily connect form controls to the model fields
            and pass the object containing all of the data back to the controller on POST

modify the controller to pass a `Cat` object to the form on GET
    `model.addAttribute("ad", new Ad());`

modify the form to use the `cat` object
    `<form th:action="@{/ads/create}" th:method="post" th:object="${ad}">`
    do we need to use the thymeleaf versions of action and method???

modify the form inputs to use the cat fields
    `<input th:field="*{name}" />`

modify the controller to receive a `Cat` object on POST
    `public String create(@ModelAttribute Cat cat) {`