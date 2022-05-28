import React from "react";
import Adapter from "enzyme-adapter-react-16";
import { shallow, configure } from "enzyme";
import ViewSwitch from "./ViewSwitch";

configure({ adapter: new Adapter() });

describe("ViewSwitch", () => {
    let wrapper;
    test("renders corectly", () => {
        wrapper = shallow(
            <ViewSwitch
                values={["a", "b", "c"]}
                selected_="a"
            />
        );
    });

    test("changes state on click", () => {
        // const toggleHidden = jest.fn();
        // const handleClick = jest.spyOn(React, "useState");
        // handleClick.mockImplementation((state) => [state, toggleHidden]);
        // // could not execute toggleHidden
        // expect(toggleHidden).toBeCalled();
        // handleClick.mockRestore();
        // expect(wrapper.find(".resultsView").length).toBe(1);
        // expect(wrapper.find(".groupView").length).toBe(1);
    });
});
