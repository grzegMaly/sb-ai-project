import {useState} from "react";

export const RecipeGenerator = () => {

    const [ingredients, setIngredients] = useState('');
    const [cuisine, setCuisine] = useState('any');
    const [dietaryRestrictions, setDietaryRestrictions] = useState('');
    const [recipe, setRecipe] = useState('');

    const createRecipe = async () => {
        try {
            const response = await fetch(`http://localhost:8080/recipe-creator?ingredients=${ingredients}&cuisine=${cuisine}&dietaryRestrictions=${dietaryRestrictions}`)
            const data = await response.text();
            setRecipe(data);
        } catch (error) {
            console.error("Error generating recipe: ", error)
        }
    }

    return (
        <div>
            <h2>Create a Recipe</h2>
            <input type="text"
                   value={ingredients}
                   onChange={e => setIngredients(e.target.value)}
                   placeholder="Enter ingredients (comma separated)"
            />
            <input type="text"
                   value={cuisine}
                   onChange={e => setCuisine(e.target.value)}
                   placeholder="Enter cuisine type"
            />
            <input type="text"
                   value={dietaryRestrictions}
                   onChange={e => setDietaryRestrictions(e.target.value)}
                   placeholder="Enter dietary restrictions"
            />

            <button onClick={createRecipe}>Create Recipe</button>
            <div className="output">
                <pre className="recipe-text">
                    {recipe}
                </pre>
            </div>
        </div>
    )
}