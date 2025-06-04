import React, { useState } from 'react';
import { assets } from '../../assets/assets';
import { ToastContainer, toast } from 'react-toastify'; 
import { addFood } from '../../services/foodservice';
const AddFood = () => {
  const [image, setImage] = useState(null);
  const [data, setData] = useState({
    name: '',
    description: '',
    price: '',
    category: 'Biryani'
  });

  const onChangeHandler = (event) => {
    const { name, value } = event.target;
    setData((prevData) => ({ ...prevData, [name]: value }));
  };

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    if (!image) {
      toast.error('Please select an image');
      return;
    }
    try {
      await addFood(data, image);
      toast.success('Food added successfully!');
      setData({ name: '', description: '',category: 'Biryani', price: '' });
      setImage(null);
    } catch (error) {
      toast.error('Error adding food!');
      throw error;
    }
  };

  return (
    <div className="mx-2 mt-2">
      <div className="row">
        <div className="card col-md-4">
          <div className="card-body">
            <h2 className="mb-4">Add Food</h2>
            <form onSubmit={onSubmitHandler}>
              <div className="mb-3">
                <label htmlFor="name" className="form-label">Name</label>
                <input type="text" placeholder='Veg  Biryani' className="form-control" id="name" required name="name" onChange={onChangeHandler} value={data.name} />
              </div>

              <div className="mb-3">
                <label htmlFor="description" className="form-label">Description</label>
                <textarea className="form-control" placeholder='Write Content here' id="description" rows="5" required name="description" onChange={onChangeHandler} value={data.description}></textarea>
              </div>

              <div className="mb-3">
                <label htmlFor="category" className="form-label">Category</label>
                  <select name="category" id="category" className='form-control' onChange={onChangeHandler} value={data.category}>
                      <option value="Biryani">Biryani</option>
                      <option value="Burger">Burger </option>
                      <option value="Pizza">Pizza</option>
                      <option value="Salad">Salad</option>
                      <option value="Sandwich">Sandwich</option>
                      <option value="Dessert">Icecream</option>
                      <option value="Rolls">Rolls</option>
                      <option value="Cakes">Cakes</option>
                  </select>
              </div>

              <div className="mb-3">
                <label htmlFor="price" className="form-label">Price</label>
                <input type="number" placeholder='&#8377;200' name="price" id="price" className="form-control" onChange={onChangeHandler} value={data.price} />
              </div>

              <div className="mb-3">
                <label htmlFor="image" className="form-label">
                  <img src={image ? URL.createObjectURL(image) : assets.upload} alt="" width={48} height={48} />
                </label>
                <input type="file" className="form-control" id="image" hidden onChange={(e) => setImage(e.target.files[0])} />
              </div>

              <button type="submit" className="btn btn-primary">Save</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddFood;
