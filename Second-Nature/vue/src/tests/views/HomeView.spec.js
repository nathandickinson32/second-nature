import { beforeEach, describe, it, expect } from "vitest";
import HomeView from "../../views/HomeView.vue";
import { mount } from "@vue/test-utils";
import { createStore } from 'vuex';
import { nextTick } from "vue";

describe('HomeView', () => {
  let wrapper;
  let store;

  beforeEach(() => {
    store = createStore({
      state: {
        user: {
          firstName: 'Bob'
        }
      },
      getters: {
        isManager: () => true,
      }
    });
  });

  it('renders with Bob as name', () => {
    wrapper = mount(HomeView, {
      global: {
        plugins: [store]
      }
    });

    const welcome = wrapper.find('h4');

    expect(welcome.text()).include('Bob');
  });

  //will need to be updated if adding/removing icons
  it('renders expected number of menu icons for a manager', () => {
    wrapper = mount(HomeView, {
      global: {
        plugins: [store]
      }
    });

    const menuIcons = wrapper.findAll('.menu-icon');

    expect(store.getters.isManager).toBe(true);
    expect(menuIcons.length).toBe(9);
  });

  it('renders expected number of menu icons for a non-manager', async () => {
    store = createStore({
      state: {
        user: {
          firstName: 'Bob'
        }
      },
      getters: {
        isManager: () => false
      }
    })
    wrapper = mount(HomeView, {
      global: {
        plugins: [store]
      }
    });

    const menuIcons = wrapper.findAll('.menu-icon');

    await nextTick();

    expect(store.getters.isManager).toBe(false);
    expect(menuIcons.length).toBe(8);
  })
});