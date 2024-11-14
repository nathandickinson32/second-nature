import axios from "axios";

export default {
    /**
     * Creates a new type.
     * Expects a Category JSON object
     * @returns a Category JSON object
     */
    createNewCategory(category) {
        return axios.put('/category/create', category);
    },
    /**
     * Gets a category by it's ID.
     * Expects a categoryId in the path
     * @returns a Category JSON object
     */
    getCategoryById(categoryId) {
        return axios.get(`/category/${categoryId}`);
    },
    /**
     * Gets all category.
     * @returns an array of Category JSON object
     */
    getAllCategories() {
        return axios.get('/category/all');
    },
    /**
     * Updates a category.
     * Expects a Category JSON object
     * @returns a Category JSON object
     */
    updateCategory(category) {
        return axios.post('/category/update', category);
    },
    /**
     * Deletes a category.
     * Expects a categoryId in the path
     * @returns nothing
     */
    deleteCategory(categoryId) {
        return axios.put(`/category/${categoryId}`);
    }
}

/**
 * JSON Objects
Type
{
	"categoryId" : -1,
	"name" : "Category Name"
}
**/
